package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.builder.bus
import codes.spectrum.message.queue.RabbitConstants
import codes.spectrum.sources.config.GlobalConfig
import codes.spectrum.sources.config.getOrDefault
import codes.spectrum.sources.demo.DemoConstants
import codes.spectrum.sources.demo.transport.bus.sourceBus
import kotlinx.coroutines.runBlocking

fun startBus() {
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

                val handlerContext = this

                runBlocking { DemoCrawlerBus.Instanse.execute(handlerContext) }
            }
        }
        
        queue(DemoConstants.DEFAULT_BUS_LOADER_QUEUE) {
            handle {
                serviceName = DemoConstants.DEFAULT_BUS_SERVICE_NAME
                serviceInput = DemoConstants.DEFAULT_BUS_LOADER_SERVICE_INPUT
                serviceError = DemoConstants.DEFAULT_BUS_LOADER_SERVICE_ERROR

                val handlerContext = this

                runBlocking { DemoLoaderBus.Instanse.execute(handlerContext) }
            }
        }
        
        queue(DemoConstants.DEFAULT_BUS_SAVER_QUEUE) {
            handle {
                serviceName = DemoConstants.DEFAULT_BUS_SERVICE_NAME
                serviceInput = DemoConstants.DEFAULT_BUS_SAVER_SERVICE_INPUT
                serviceError = DemoConstants.DEFAULT_BUS_SAVER_SERVICE_ERROR

                val handlerContext = this

                runBlocking { DemoSaverBus.Instanse.execute(handlerContext) }
            }
        }
    }

}