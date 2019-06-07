package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.demo.transport.saver.*
import codes.spectrum.sources.core.service.RestSourceAdapter

class BalanceGksSaverRest(internalSource: IBalanceGksSaverSource = BalanceGksSaver()):
    RestSourceAdapter<BalanceGksSaverQuery, BalanceGksSaverData>(internalSource, BalanceGksSaverContext::class.java){
    companion object{
        val Instanse by lazy{ BalanceGksSaverRest() }
    }
}
