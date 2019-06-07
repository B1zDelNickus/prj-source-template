package codes.spectrum.sources.demo.transport.crawler

import codes.spectrum.sources.DebugMode
import codes.spectrum.sources.SourceQuery

class BalanceGksCrawlerRequest(
    query: BalanceGksCrawlerQuery = BalanceGksCrawlerQuery(),
    debug: DebugMode = DebugMode(),
    timeout: Long = -1L
) : SourceQuery<BalanceGksCrawlerQuery>(query = query) {
    init {
        this.debug = debug
        this.timeout = timeout
    }
}