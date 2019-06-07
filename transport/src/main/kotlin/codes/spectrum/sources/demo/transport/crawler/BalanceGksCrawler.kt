package codes.spectrum.sources.demo.transport.crawler

import codes.spectrum.konveyor.DefaultKonveyorEnvironment
import codes.spectrum.konveyor.konveyor
import codes.spectrum.sources.config.IConfig
import codes.spectrum.sources.core.SourceState

class BalanceGksCrawler : IBalanceGksCrawlerSource {
    override suspend fun execute(context: BalanceGksCrawlerContext, config: IConfig) {
        try {
            val env = DefaultKonveyorEnvironment
            konveyor.exec(context, env)
        } catch (exception: Throwable) {
            context.result.error = exception
            context.result.status = SourceState.GENERAL_ERROR
        }
    }

    companion object {
        val konveyor = konveyor<BalanceGksCrawlerContext> {
            exec {
                result.status = SourceState.OK
            }
        }
    }
}