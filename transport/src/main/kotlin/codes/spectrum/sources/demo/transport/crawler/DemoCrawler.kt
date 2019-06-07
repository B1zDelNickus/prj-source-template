package codes.spectrum.sources.demo.transport.crawler

import codes.spectrum.konveyor.DefaultKonveyorEnvironment
import codes.spectrum.konveyor.konveyor
import codes.spectrum.sources.config.IConfig
import codes.spectrum.sources.core.SourceState

class DemoCrawler : IDemoCrawlerSource {
    override suspend fun execute(context: DemoCrawlerContext, config: IConfig) {
        try {
            val env = DefaultKonveyorEnvironment
            konveyor.exec(context, env)
        } catch (exception: Throwable) {
            context.result.error = exception
            context.result.status = SourceState.GENERAL_ERROR
        }
    }

    companion object {
        val konveyor = konveyor<DemoCrawlerContext> {
            exec {
                result.status = SourceState.OK
            }
        }
    }
}