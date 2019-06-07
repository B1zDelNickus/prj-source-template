package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.core.service.RestSourceAdapter
import codes.spectrum.sources.demo.transport.crawler.*

class DemoCrawlerRest(internalSource: IDemoCrawlerSource = DemoCrawler()):
    RestSourceAdapter<DemoCrawlerQuery, DemoCrawlerData>(internalSource, DemoCrawlerContext::class.java){
    companion object{
        val Instanse by lazy{ DemoCrawlerRest() }
    }
}
