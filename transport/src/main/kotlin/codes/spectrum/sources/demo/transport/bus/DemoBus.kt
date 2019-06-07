package codes.spectrum.sources.demo.transport.bus

import codes.spectrum.bus.builder.buildBus
import codes.spectrum.sources.demo.DemoConstants

val sourceBus = buildBus(DemoConstants.SOURCE_ID) {
    topic(DemoConstants.EXCHANGE_NAME) {
        route(DemoConstants.DEFAULT_BUS_SERVICE_MAIN_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_SERVICE_MAIN_QUEUE)
        }
        route(DemoConstants.DEFAULT_BUS_SERVICE_ERROR_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_SERVICE_ERROR_QUEUE)
        }
    }
    delay()

    service(DemoConstants.DEFAULT_BUS_SERVICE_NAME) {
        input(DemoConstants.DEFAULT_BUS_SERVICE_INPUT_NAME, DemoConstants.DEFAULT_BUS_SERVICE_MAIN_QUEUE)
        out(DemoConstants.DEFAULT_BUS_SERVICE_OUTPUT_NAME, DemoConstants.DEFAULT_BUS_SERVICE_MAIN_ROUTE)
        out(DemoConstants.DEFAULT_BUS_SERVICE_ERROR_NAME, DemoConstants.DEFAULT_BUS_SERVICE_ERROR_ROUTE)
    }
}