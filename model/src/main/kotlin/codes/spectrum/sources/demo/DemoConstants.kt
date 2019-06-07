package codes.spectrum.sources.demo

object DemoConstants {
    const val SOURCE_ID = "SOURCE_ID"

    const val EXCHANGE_NAME = "$SOURCE_ID-exchange"

    const val DEFAULT_BUS_SERVICE_MAIN_ROUTE = "$SOURCE_ID.agent.service.main"
    const val DEFAULT_BUS_SERVICE_MAIN_QUEUE = "$SOURCE_ID-agent-service-main"
    const val DEFAULT_BUS_SERVICE_ERROR_ROUTE = "$SOURCE_ID.agent.service.error"
    const val DEFAULT_BUS_SERVICE_ERROR_QUEUE = "$SOURCE_ID-agent-service-error"

    const val DEFAULT_BUS_SERVICE_NAME = "$SOURCE_ID-agent-service"
    const val DEFAULT_BUS_SERVICE_INPUT_NAME = "$SOURCE_ID.agent.service.input"
    const val DEFAULT_BUS_SERVICE_OUTPUT_NAME = "$SOURCE_ID.agent.service.output"
    const val DEFAULT_BUS_SERVICE_ERROR_NAME = "$SOURCE_ID.agent.service.error"
}