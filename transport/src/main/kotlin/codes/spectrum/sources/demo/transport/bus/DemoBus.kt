package codes.spectrum.sources.demo.transport.bus

import codes.spectrum.bus.builder.buildBus
import codes.spectrum.sources.demo.DemoConstants

val sourceBus = buildBus(DemoConstants.SOURCE_ID) {
    topic(DemoConstants.EXCHANGE_NAME) {
        route(DemoConstants.DEFAULT_BUS_AGENT_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_AGENT_QUEUE)
        }
        route(DemoConstants.DEFAULT_BUS_AGENT_ERROR_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_AGENT_ERROR_QUEUE)
        }
    }

    delay()

    service(DemoConstants.DEFAULT_BUS_AGENT_SERVICE_NAME) {
        input(DemoConstants.DEFAULT_BUS_AGENT_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_AGENT_QUEUE)
        out(DemoConstants.DEFAULT_BUS_AGENT_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_AGENT_ROUTE)
        out(DemoConstants.DEFAULT_BUS_AGENT_SERVICE_ERROR, DemoConstants.DEFAULT_BUS_AGENT_ERROR_ROUTE)
    }

}