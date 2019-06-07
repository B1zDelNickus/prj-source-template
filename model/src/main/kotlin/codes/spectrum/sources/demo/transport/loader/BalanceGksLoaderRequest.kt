package codes.spectrum.sources.demo.transport.loader

import codes.spectrum.sources.DebugMode
import codes.spectrum.sources.SourceQuery

class BalanceGksLoaderRequest(
    query: BalanceGksLoaderQuery = BalanceGksLoaderQuery(),
    debug: DebugMode = DebugMode(),
    timeout: Long = -1L
) : SourceQuery<BalanceGksLoaderQuery>(query = query) {
    init {
        this.debug = debug
        this.timeout = timeout
    }
}