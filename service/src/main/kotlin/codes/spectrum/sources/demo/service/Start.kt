package codes.spectrum.sources.demo.service

import codes.spectrum.sources.core.SourceDefinition
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory

val InstanceLog = LoggerFactory.getLogger(SourceDefinition.Instance.serviceLoggerName)
fun main() {
    InstanceLog.info("Enter source ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    try {
        embeddedServer(Netty
            , 8080
            , module = module)
            .start(wait = true)
    }catch(e:Throwable){
        InstanceLog.error("Error source ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})",e)
    }finally {
        InstanceLog.info("Finish source ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    }
}
