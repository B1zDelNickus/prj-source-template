package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.core.service.RestSourceAdapter
import codes.spectrum.sources.demo.transport.loader.*

class BalanceGksLoaderRest(internalSource: IBalanceGksLoaderSource = BalanceGksLoader()):
    RestSourceAdapter<BalanceGksLoaderQuery, BalanceGksLoaderData>(internalSource, BalanceGksLoaderContext::class.java){
    companion object{
        val Instanse by lazy{ BalanceGksLoaderRest() }
    }
}
