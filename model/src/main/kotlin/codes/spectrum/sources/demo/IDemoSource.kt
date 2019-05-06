package codes.spectrum.sources.demo

import codes.spectrum.sources.ISourceHandler
import codes.spectrum.sources.SourceContext
import codes.spectrum.sources.SourceQuery
import codes.spectrum.sources.SourceResult
import codes.spectrum.sources.config.IConfig
import codes.spectrum.sources.demo.model.DemoData
import codes.spectrum.sources.demo.model.DemoQuery


interface IDemoSource : ISourceHandler<DemoContext> {
    object Empty : IDemoSource {
        override suspend fun execute(context: DemoContext, config: IConfig) {

        }
    }
}

class DemoRequest(query:DemoQuery = DemoQuery() ):SourceQuery<DemoQuery>(query=query)
class DemoResult(data:DemoData = DemoData()):SourceResult<DemoData>(data=data)
class DemoContext(query: DemoRequest = DemoRequest(), result: DemoResult = DemoResult()) : SourceContext<DemoQuery, DemoData>(query = query, result = result)


