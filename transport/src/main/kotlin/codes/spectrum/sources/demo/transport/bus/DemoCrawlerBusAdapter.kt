package codes.spectrum.sources.demo.transport.bus

import codes.spectrum.message.Message
import codes.spectrum.sources.core.SourceState
import codes.spectrum.sources.demo.transport.crawler.DemoCrawler
import codes.spectrum.sources.demo.transport.crawler.DemoCrawlerContext
import codes.spectrum.sources.demo.transport.crawler.DemoCrawlerQuery
import codes.spectrum.sources.demo.transport.crawler.DemoCrawlerRequest
import codes.spectrum.sources.demo.transport.exceptions.IllegalMessageException
import org.slf4j.LoggerFactory

class DemoCrawlerBusAdapter : IBusSourceAdapter {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val source = DemoCrawler()

    override suspend fun execute(message: Message): SourceState = try {
        val context = DemoCrawlerContext(
            query = DemoCrawlerRequest(
                query = message.getRequestQuery<DemoCrawlerQuery>() ?: throw IllegalMessageException("Can't receive CrawlerQuery from message"),
                debug = message.getRequestDebug() ?: throw IllegalMessageException("Can't receive debug from message"),
                timeout = message.getRequestTimeout() ?: throw IllegalMessageException("Can't receive timeout from message")
            )
        )
        source.execute(context)
        context.result.status
    } catch (e: Throwable) {
        logger.error("Error in crawler rabbit adapter", e)
        SourceState.GENERAL_ERROR
    }
}