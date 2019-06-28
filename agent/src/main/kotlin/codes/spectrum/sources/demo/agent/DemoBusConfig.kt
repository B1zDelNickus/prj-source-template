package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.builder.bus
import codes.spectrum.bus.rabbit.BusRabbitInitializer
import codes.spectrum.message.queue.toMessage
import codes.spectrum.sources.config.GlobalConfig
import codes.spectrum.sources.config.getOrDefault
import codes.spectrum.sources.core.agent.AgentMessageTask
import codes.spectrum.sources.demo.DemoConstants
import codes.spectrum.sources.demo.transport.bus.connectionFactory
import codes.spectrum.sources.demo.transport.bus.navigator
import codes.spectrum.sources.demo.transport.bus.sourceBus

object DemoBusConfig {
    fun startBus() {
        if (GlobalConfig.getOrDefault("initRabbit", "false").toBoolean()) {
            BusRabbitInitializer(connectionFactory, sourceBus, true)
        }

        bus {
            navigator(navigator)

            queue(DemoConstants.DEFAULT_BUS_AGENT_QUEUE) {
                handle {
                    serviceName = DemoConstants.DEFAULT_BUS_AGENT_SERVICE_NAME
                    serviceInput = DemoConstants.DEFAULT_BUS_AGENT_SERVICE_INPUT
                    serviceError = DemoConstants.DEFAULT_BUS_AGENT_SERVICE_ERROR

                    val task = AgentMessageTask.valueOf(message.toMessage().header.task.name.toUpperCase())
                    when(task) {
                        AgentMessageTask.CRAWL -> {
                            DemoCrawlerBus.Instanse.execute(this)
                        }
                        AgentMessageTask.SAVE -> {
                            DemoSaverBus.Instanse.execute(this)
                        }
                        AgentMessageTask.LOAD -> {
                            DemoCrawlerBus.Instanse.execute(this)
                        }
                    }
                }
            }
        }
    }

}