package codes.spectrum.sources.demo.service.bus

import codes.spectrum.bus.builder.buildBus
import codes.spectrum.sources.demo.DemoConstants

val sourceBus = buildBus(DemoConstants.SOURCE_ID) {
    topic(DemoConstants.exchangeName) {
        route(DemoConstants.rabbitServiceMainRoute) {
            queue(DemoConstants.rabbitServiceMainQueue)
        }
        route(DemoConstants.rabbitServiceErrorRoute) {
            queue(DemoConstants.rabbitServiceErrorQueue)
        }
    }
    delay()

    service(DemoConstants.rabbitServiceName) {
        input(DemoConstants.rabbitServiceInputName, DemoConstants.rabbitServiceMainQueue)
        out(DemoConstants.rabbitServiceOutputName, DemoConstants.rabbitServiceMainRoute)
        out(DemoConstants.rabbitServiceErrorName, DemoConstants.rabbitServiceErrorRoute)
    }
}