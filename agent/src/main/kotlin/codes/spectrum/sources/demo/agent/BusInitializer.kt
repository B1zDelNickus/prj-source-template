package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.integration.BusNavigator
import codes.spectrum.message.Message
import codes.spectrum.message.queue.RabbitConstants
import codes.spectrum.sources.config.EnvProxy
import codes.spectrum.sources.config.GlobalConfig
import codes.spectrum.sources.config.get
import codes.spectrum.sources.core.SourceState
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import codes.spectrum.sources.core.service.bus.BusListenerService
import codes.spectrum.sources.core.service.bus.BusSenderService
import codes.spectrum.sources.demo.DemoConstants
import codes.spectrum.sources.demo.service.bus.*

object BusInitializer {
    private val defaultHandler: suspend (Message) -> SourceState = {
        message: Message -> SourceState.OK
    }

    /**
     * Запуск rabbit сервиса
     */
    fun start(
        rabbitHost: String? = null,
        rabbitPort: Int? = null,
        rabbitUser: String? = null,
        rabbitPassword: String? = null
    ) {
        val busNavigator = createBusNavigator(rabbitHost, rabbitPort, rabbitUser, rabbitPassword)
        val rabbitListenerService = createDefaultRabbitListenerService(busNavigator)
        rabbitListenerService.start()
        val rabbitSenderService = createDefaultRabbitSenderService(busNavigator)

        EnvProxy.Instance.set("busNavigator", busNavigator)
        EnvProxy.Instance.set("rabbitListenerService", rabbitListenerService)
        EnvProxy.Instance.set("rabbitSenderService", rabbitSenderService)
    }

    /**
     * Создать BusNavigator
     */
    private fun createBusNavigator(
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
    private fun createDefaultRabbitListenerService(busNavigator: BusNavigator) = BusListenerService(
        navigator = busNavigator,
        serviceName = DemoConstants.rabbitServiceName,
        serviceInputName = DemoConstants.rabbitServiceInputName,
        serviceErrorName = DemoConstants.rabbitServiceErrorName,
        handler = defaultHandler
    )

    /**
     * Создать BusSenderService
     */
    private fun createDefaultRabbitSenderService(busNavigator: BusNavigator) = BusSenderService(
        navigator = busNavigator,
        serviceName = DemoConstants.rabbitServiceName,
        serviceOutputName = DemoConstants.rabbitServiceOutputName
    )

}
