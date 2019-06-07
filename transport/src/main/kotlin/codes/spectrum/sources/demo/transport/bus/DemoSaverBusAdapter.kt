package codes.spectrum.sources.demo.transport.bus

import codes.spectrum.message.Message
import codes.spectrum.sources.core.SourceState
import codes.spectrum.sources.core.transport.bus.IBusSourceAdapter
import codes.spectrum.sources.core.transport.bus.getRequestDebug
import codes.spectrum.sources.core.transport.bus.getRequestQuery
import codes.spectrum.sources.core.transport.bus.getRequestTimeout
import codes.spectrum.sources.core.transport.exceptions.IllegalMessageException
import codes.spectrum.sources.demo.transport.saver.DemoSaver
import codes.spectrum.sources.demo.transport.saver.DemoSaverContext
import codes.spectrum.sources.demo.transport.saver.DemoSaverQuery
import codes.spectrum.sources.demo.transport.saver.DemoSaverRequest
import org.slf4j.LoggerFactory

class DemoSaverBusAdapter : IBusSourceAdapter {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val source = DemoSaver()

    override suspend fun execute(message: Message) = try {
        val context = DemoSaverContext(
            query = DemoSaverRequest(
                query = message.getRequestQuery<DemoSaverQuery>() ?: throw IllegalMessageException("Can't receive SaverQuery from message"),
                debug = message.getRequestDebug() ?: throw IllegalMessageException("Can't receive debug from message"),
                timeout = message.getRequestTimeout() ?: throw IllegalMessageException("Can't receive timeout from message")
            )
        )
        source.execute(context)
        context.result.status
    } catch (e: Throwable) {
        logger.error("Error in saver rabbit adapter", e)
        SourceState.GENERAL_ERROR
    }

}