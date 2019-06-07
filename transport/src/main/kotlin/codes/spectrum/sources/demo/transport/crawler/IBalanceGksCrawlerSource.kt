package codes.spectrum.sources.demo.transport.crawler

import codes.spectrum.sources.ISourceHandler
import codes.spectrum.sources.config.IConfig

interface IBalanceGksCrawlerSource : ISourceHandler<BalanceGksCrawlerContext> {
    object Empty : IBalanceGksCrawlerSource {
        override suspend fun execute(context: BalanceGksCrawlerContext, config: IConfig){

        }
    }
}
