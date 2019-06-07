package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.core.service.RestSourceAdapter
import codes.spectrum.sources.demo.transport.crawler.*

class BalanceGksCrawlerRest(internalSource: IBalanceGksCrawlerSource = BalanceGksCrawler()):
    RestSourceAdapter<BalanceGksCrawlerQuery, BalanceGksCrawlerData>(internalSource, BalanceGksCrawlerContext::class.java){
    companion object{
        val Instanse by lazy{ BalanceGksCrawlerRest() }
    }
}
