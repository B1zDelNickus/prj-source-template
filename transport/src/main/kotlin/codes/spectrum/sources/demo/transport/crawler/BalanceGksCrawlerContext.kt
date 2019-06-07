package codes.spectrum.sources.demo.transport.crawler

import codes.spectrum.sources.SourceContext

class BalanceGksCrawlerContext(
    query: BalanceGksCrawlerRequest = BalanceGksCrawlerRequest(),
    result: BalanceGksCrawlerResult = BalanceGksCrawlerResult()
) : SourceContext<BalanceGksCrawlerQuery, BalanceGksCrawlerData>(query = query, result = result)