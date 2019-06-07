package codes.spectrum.sources.demo.transport.saver

import codes.spectrum.sources.ISourceHandler
import codes.spectrum.sources.config.IConfig

interface IBalanceGksSaverSource : ISourceHandler<BalanceGksSaverContext> {
    object Empty : IBalanceGksSaverSource {
        override suspend fun execute(context: BalanceGksSaverContext, config: IConfig){

        }
    }
}
