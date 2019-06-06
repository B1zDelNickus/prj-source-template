package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.core.SourceDefinition
import org.slf4j.LoggerFactory

val InstanceLog = LoggerFactory.getLogger(SourceDefinition.Instance.serviceLoggerName)
fun main() {
    InstanceLog.info("Enter agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    try {
        // Запуск bus сервиса
        BusInitializer.start()

    }catch(e:Throwable){
        InstanceLog.error("Error agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})",e)
    }finally {
        InstanceLog.info("Finish agent ${SourceDefinition.Instance.name} (${SourceDefinition.Instance.code})")
    }
}
