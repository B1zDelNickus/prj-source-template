package codes.spectrum.sources.demo.transport.loader

import codes.spectrum.sources.ISourceHandler
import codes.spectrum.sources.config.IConfig

interface IBalanceGksLoaderSource : ISourceHandler<BalanceGksLoaderContext> {
    object Empty : IBalanceGksLoaderSource {
        override suspend fun execute(context: BalanceGksLoaderContext, config: IConfig){

        }
    }
}
