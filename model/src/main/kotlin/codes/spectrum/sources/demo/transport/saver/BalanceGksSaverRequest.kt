package codes.spectrum.sources.demo.transport.saver

import codes.spectrum.sources.DebugMode
import codes.spectrum.sources.SourceQuery

class BalanceGksSaverRequest(
    query: BalanceGksSaverQuery = BalanceGksSaverQuery(),
    debug: DebugMode = DebugMode(),
    timeout: Long = -1L
) : SourceQuery<BalanceGksSaverQuery>(query = query) {
    init {
        this.debug = debug
        this.timeout = timeout
    }
}