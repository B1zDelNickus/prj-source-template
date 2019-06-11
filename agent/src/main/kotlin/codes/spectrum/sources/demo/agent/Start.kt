package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.builder.bus
import codes.spectrum.message.queue.RabbitConstants
import codes.spectrum.sources.config.GlobalConfig
import codes.spectrum.sources.config.getOrDefault
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
        bus {
            connection {
                host = GlobalConfig.getOrDefault("rabbitHost", RabbitConstants.DEFAULT_PROD_HOST)
                port = GlobalConfig.getOrDefault("rabbitPort", RabbitConstants.DEFAULT_PROD_PORT)
                user = GlobalConfig.getOrDefault("rabbitUser", RabbitConstants.DEFAULT_PROD_USER)
                password = GlobalConfig.getOrDefault("rabbitPassword", RabbitConstants.DEFAULT_PROD_PASS)
            }
            bus(sourceBus)
            queue(DemoConstants.DEFAULT_BUS_CRAWLER_QUEUE) {
                handle {
                    serviceName = DemoConstants.DEFAULT_BUS_SERVICE_NAME
                    serviceInput = DemoConstants.DEFAULT_BUS_CRAWLER_SERVICE_INPUT
                    serviceError = DemoConstants.DEFAULT_BUS_CRAWLER_SERVICE_ERROR

                    DemoCrawlerBus.Instanse.execute(this)
                }
            }
        }

        // Запуск web сервера
        embeddedServer(Netty, 8080, module = module).start(wait = true)
    } catch (e: Throwable) {
        InstanceLog.error("Error agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})", e)
    } finally {
        InstanceLog.info("Finish agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    }
}
