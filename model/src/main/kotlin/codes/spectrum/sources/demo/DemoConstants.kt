package codes.spectrum.sources.demo

object DemoConstants {
    const val SOURCE_ID = "SOURCE_ID"

    const val exchangeName = "$SOURCE_ID-exchange"

    const val rabbitServiceMainRoute = "$SOURCE_ID.agent.service.main"
    const val rabbitServiceMainQueue = "$SOURCE_ID-agent-service-main"
    const val rabbitServiceErrorRoute = "$SOURCE_ID.agent.service.error"
    const val rabbitServiceErrorQueue = "$SOURCE_ID-agent-service-error"

    const val rabbitServiceName = "$SOURCE_ID-agent-service"
    const val rabbitServiceInputName = "$SOURCE_ID.agent.service.input"
    const val rabbitServiceOutputName = "$SOURCE_ID.agent.service.output"
    const val rabbitServiceErrorName = "$SOURCE_ID.agent.service.error"
}