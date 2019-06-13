package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.builder.bus
import codes.spectrum.bus.integration.BusNavigator
import codes.spectrum.message.queue.RabbitConstants
import codes.spectrum.message.queue.toMessage
import codes.spectrum.sources.config.GlobalConfig
import codes.spectrum.sources.config.getOrDefault
import codes.spectrum.sources.core.agent.AgentMessageTask
import codes.spectrum.sources.demo.DemoConstants
import codes.spectrum.sources.demo.transport.bus.sourceBus
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory

object DemoBusConfig {
    private val host = GlobalConfig.getOrDefault("rabbitHost", RabbitConstants.DEFAULT_PROD_HOST)
    private val port = GlobalConfig.getOrDefault("rabbitPort", RabbitConstants.DEFAULT_PROD_PORT)
    private val user = GlobalConfig.getOrDefault("rabbitUser", RabbitConstants.DEFAULT_PROD_USER)
    private val password = GlobalConfig.getOrDefault("rabbitPassword", RabbitConstants.DEFAULT_PROD_PASS)

    private val connectionFactory by lazy { CachingConnectionFactory(host, port).apply {
            setUsername(user)
            setPassword(password)
        }
    }

    val navigator by lazy { BusNavigator(sourceBus, connectionFactory) }

    fun startBus() {
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