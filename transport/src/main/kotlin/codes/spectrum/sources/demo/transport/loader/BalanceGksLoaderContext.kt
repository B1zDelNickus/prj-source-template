package codes.spectrum.sources.demo.transport.loader

import codes.spectrum.sources.SourceContext

class BalanceGksLoaderContext(
    query: BalanceGksLoaderRequest = BalanceGksLoaderRequest(),
    result: BalanceGksLoaderResult = BalanceGksLoaderResult()
) : SourceContext<BalanceGksLoaderQuery, BalanceGksLoaderData>(query = query, result = result)
