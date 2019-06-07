package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.core.SourceDefinition
import codes.spectrum.sources.demo.transport.bus.sourceBus
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory
import codes.spectrum.sources.demo.DemoConstants


val InstanceLog = LoggerFactory.getLogger(SourceDefinition.Instance.serviceLoggerName)
fun main() {
    InstanceLog.info("Enter agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    try {
        // Запуск bus сервиса
        DemoBusInitializer(
            bus = sourceBus,
            serviceName = DemoConstants.DEFAULT_BUS_SERVICE_NAME,
            serviceInputName = DemoConstants.DEFAULT_BUS_SERVICE_INPUT_NAME,
            serviceOutputName = DemoConstants.DEFAULT_BUS_SERVICE_OUTPUT_NAME,
            serviceErrorName = DemoConstants.DEFAULT_BUS_SERVICE_ERROR_NAME
        ).start()

        // Запуск web сервера
        embeddedServer(Netty, 8080, module = module).start(wait = true)
    } catch (e: Throwable) {
        InstanceLog.error("Error agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})", e)
    } finally {
        InstanceLog.info("Finish agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    }
}
