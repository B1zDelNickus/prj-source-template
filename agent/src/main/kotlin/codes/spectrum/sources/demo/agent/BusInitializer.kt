package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.integration.BusNavigator
import codes.spectrum.message.Message
import codes.spectrum.message.queue.RabbitConstants
import codes.spectrum.sources.config.EnvProxy
import codes.spectrum.sources.config.GlobalConfig
import codes.spectrum.sources.config.get
import codes.spectrum.sources.core.SourceState
import codes.spectrum.sources.demo.DemoConstants
import codes.spectrum.sources.demo.transport.bus.DemoCrawlerBusAdapter
import codes.spectrum.sources.demo.transport.bus.DemoLoaderBusAdapter
import codes.spectrum.sources.demo.transport.bus.sourceBus
import codes.spectrum.sources.demo.transport.bus.DemoSaverBusAdapter
import codes.spectrum.sources.demo.transport.exceptions.UnknownAgentTaskException
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate

object BusInitializer {
    private val defaultHandler: suspend (Message) -> SourceState = { message: Message ->
        val task = try { AgentMessageTask.valueOf(message.header.task.name) } catch (e: Throwable) { throw UnknownAgentTaskException("Unknown agent task exception", e) }
        when(task) {
            AgentMessageTask.CRAWL -> DemoCrawlerBusAdapter().execute(message)
            AgentMessageTask.LOAD -> DemoLoaderBusAdapter().execute(message)
            AgentMessageTask.SAVE -> DemoSaverBusAdapter().execute(message)
        }
    }

    /**
     * Запуск bus сервиса
     */
    fun start(
        rabbitHost: String? = null,
        rabbitPort: Int? = null,
        rabbitUser: String? = null,
        rabbitPassword: String? = null
    ) {
        val busNavigator = createBusNavigator(rabbitHost, rabbitPort, rabbitUser, rabbitPassword)
        val rabbitListenerService = createDefaultBusListenerService(busNavigator)
        rabbitListenerService.start()
        val rabbitSenderService = createDefaultBusSenderService(busNavigator)

        EnvProxy.Instance.set("busNavigator", busNavigator)
        EnvProxy.Instance.set("rabbitListenerService", rabbitListenerService)
        EnvProxy.Instance.set("rabbitSenderService", rabbitSenderService)
    }

    /**
     * Создать BusNavigator
     */
    fun createBusNavigator(
        rabbitHost: String? = null,
        rabbitPort: Int? = null,
        rabbitUser: String? = null,
        rabbitPassword: String? = null
    ): BusNavigator {
        val host = rabbitHost ?: GlobalConfig.get<String>("rabbitHost") ?: RabbitConstants.DEFAULT_PROD_HOST
        val port = rabbitPort ?: GlobalConfig.get<String>("rabbitPort")?.toInt() ?: RabbitConstants.DEFAULT_PROD_PORT
        val user = rabbitUser ?: GlobalConfig.get<String>("rabbitUser") ?: RabbitConstants.DEFAULT_PROD_USER
        val password = rabbitPassword ?: GlobalConfig.get<String>("rabbitPassword") ?: RabbitConstants.DEFAULT_PROD_PASS
        val connectionFactory = CachingConnectionFactory(host, port).apply { setUsername(user); setPassword(password) }
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        return BusNavigator(sourceBus, rabbitTemplate)
    }

    /**
     * Создать BusListenerService
     */
    private fun createDefaultBusListenerService(busNavigator: BusNavigator) = BusListenerService(
        navigator = busNavigator,
        serviceName = DemoConstants.DEFAULT_BUS_SERVICE_NAME,
        serviceInputName = DemoConstants.DEFAULT_BUS_SERVICE_INPUT_NAME,
        serviceErrorName = DemoConstants.DEFAULT_BUS_SERVICE_ERROR_NAME,
        handler = defaultHandler
    )

    /**
     * Создать BusSenderService
     */
    private fun createDefaultBusSenderService(busNavigator: BusNavigator) = BusSenderService(
        navigator = busNavigator,
        serviceName = DemoConstants.DEFAULT_BUS_SERVICE_NAME,
        serviceOutputName = DemoConstants.DEFAULT_BUS_SERVICE_OUTPUT_NAME
    )

}
