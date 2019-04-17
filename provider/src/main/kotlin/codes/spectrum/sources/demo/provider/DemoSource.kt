package codes.spectrum.sources.demo.provider

import codes.spectrum.sources.SourceContext
import codes.spectrum.sources.config.IConfig
import codes.spectrum.sources.config.getOrDefault
import codes.spectrum.sources.demo.IDemoSource
import codes.spectrum.sources.demo.model.DemoData
import codes.spectrum.sources.demo.model.DemoQuery

class DemoSource : IDemoSource {
    override suspend fun execute(context: SourceContext<DemoQuery, DemoData>, config: IConfig) {
        context.result.data.answer = "${context.query.query.query}   ${config.getOrDefault("DEMO_MESSAGE","processed")}"
    }
}