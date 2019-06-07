package codes.spectrum.sources.demo.transport.bus

import codes.spectrum.message.Message
import codes.spectrum.sources.core.SourceState
import codes.spectrum.sources.core.transport.bus.IBusSourceAdapter
import codes.spectrum.sources.core.transport.bus.getRequestDebug
import codes.spectrum.sources.core.transport.bus.getRequestQuery
import codes.spectrum.sources.core.transport.bus.getRequestTimeout
import codes.spectrum.sources.core.transport.exceptions.IllegalMessageException
import codes.spectrum.sources.demo.transport.loader.DemoLoader
import codes.spectrum.sources.demo.transport.loader.DemoLoaderContext
import codes.spectrum.sources.demo.transport.loader.DemoLoaderQuery
import codes.spectrum.sources.demo.transport.loader.DemoLoaderRequest
import org.slf4j.LoggerFactory

class DemoLoaderBusAdapter : IBusSourceAdapter {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val source = DemoLoader()

    override suspend fun execute(message: Message) = try {
        val context = DemoLoaderContext(
            query = DemoLoaderRequest(
                query = message.getRequestQuery<DemoLoaderQuery>() ?: throw IllegalMessageException("Can't receive LoaderQuery from message"),
                debug = message.getRequestDebug() ?: throw IllegalMessageException("Can't receive debug from message"),
                timeout = message.getRequestTimeout() ?: throw IllegalMessageException("Can't receive timeout from message")
            )
        )


        source.execute(context)
        context.result.status
    } catch (e: Throwable) {
        logger.error("Error in loader rabbit adapter", e)
        SourceState.GENERAL_ERROR
    }

}