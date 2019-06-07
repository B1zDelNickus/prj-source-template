package codes.spectrum.sources.demo.transport.saver

import codes.spectrum.sources.SourceContext

class BalanceGksSaverContext(
    query: BalanceGksSaverRequest = BalanceGksSaverRequest(),
    result: BalanceGksSaverResult = BalanceGksSaverResult()
) : SourceContext<BalanceGksSaverQuery, BalanceGksSaverData>(query = query, result = result)
