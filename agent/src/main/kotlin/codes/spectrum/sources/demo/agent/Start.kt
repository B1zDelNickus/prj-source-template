package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.integration.BusNavigator
import codes.spectrum.message.Message
import codes.spectrum.message.MessageBody
import codes.spectrum.message.MessageHeader
import codes.spectrum.sources.core.SourceDefinition
import codes.spectrum.sources.demo.DemoConstants
import codes.spectrum.sources.demo.transport.bus.sourceBus
import codes.spectrum.sources.demo.transport.crawler.DemoCrawlerRequest
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import java.util.*


val InstanceLog = LoggerFactory.getLogger(SourceDefinition.Instance.serviceLoggerName)
fun main() {
    InstanceLog.info("Enter agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    try {
        // Запуск bus сервиса
        startBus()

        // Запуск web сервера
        embeddedServer(Netty, 8080, module = module).start(wait = true)
    } catch (e: Throwable) {
        InstanceLog.error("Error agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})", e)
    } finally {
        InstanceLog.info("Finish agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    }
}
