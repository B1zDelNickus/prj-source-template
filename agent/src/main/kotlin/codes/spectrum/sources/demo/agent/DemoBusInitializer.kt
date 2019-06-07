package codes.spectrum.sources.demo.agent

import codes.spectrum.bus.Bus
import codes.spectrum.message.Message
import codes.spectrum.sources.core.SourceState
import codes.spectrum.sources.core.agent.AgentMessageTask
import codes.spectrum.sources.core.agent.BusInitializer
import codes.spectrum.sources.core.transport.exceptions.UnknownAgentTaskException
import codes.spectrum.sources.demo.transport.bus.DemoCrawlerBusAdapter
import codes.spectrum.sources.demo.transport.bus.DemoLoaderBusAdapter
import codes.spectrum.sources.demo.transport.bus.DemoSaverBusAdapter

class DemoBusInitializer(
    bus: Bus,
    serviceName: String,
    serviceInputName: String,
    serviceErrorName: String,
    serviceOutputName: String,
    rabbitHost: String? = null,
    rabbitPort: Int? = null,
    rabbitUser: String? = null,
    rabbitPassword: String? = null
) : BusInitializer(
    bus,
    serviceName,
    serviceInputName,
    serviceErrorName,
    serviceOutputName,
    rabbitHost,
    rabbitPort,
    rabbitUser,
    rabbitPassword
) {
    override val handler: suspend (Message) -> SourceState = { message: Message ->
        val task = try { AgentMessageTask.valueOf(message.header.task.name) } catch (e: Throwable) { throw UnknownAgentTaskException("Unknown agent task exception", e) }
        when(task) {
            AgentMessageTask.CRAWL -> DemoCrawlerBusAdapter().execute(message)
            AgentMessageTask.LOAD -> DemoLoaderBusAdapter().execute(message)
            AgentMessageTask.SAVE -> DemoSaverBusAdapter().execute(message)
        }
    }
}