package codes.spectrum.sources.demo.transport.saver

import codes.spectrum.konveyor.DefaultKonveyorEnvironment
import codes.spectrum.konveyor.konveyor
import codes.spectrum.sources.config.IConfig
import codes.spectrum.sources.core.SourceState

class DemoSaver : IDemoSaverSource {
    override suspend fun execute(context: DemoSaverContext, config: IConfig) {
        try {
            konveyor.exec(context, config)
        } catch (exception: Throwable) {
            context.result.error = exception
            context.result.status = SourceState.GENERAL_ERROR
        }
    }

    companion object {
        val konveyor = konveyor<DemoSaverContext> {
            exec {
                result.status = SourceState.OK
            }
        }
    }
}