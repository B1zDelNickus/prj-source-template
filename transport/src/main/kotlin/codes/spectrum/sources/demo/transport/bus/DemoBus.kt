package codes.spectrum.sources.demo.transport.bus

import codes.spectrum.bus.builder.buildBus
import codes.spectrum.sources.demo.DemoConstants

val sourceBus = buildBus {
    topic(DemoConstants.EXCHANGE_NAME) {
        route(DemoConstants.DEFAULT_BUS_CRAWLER_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_CRAWLER_QUEUE)
        }
        route(DemoConstants.DEFAULT_BUS_CRAWLER_ERROR_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_CRAWLER_ERROR_QUEUE)
        }

        route(DemoConstants.DEFAULT_BUS_LOADER_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_LOADER_QUEUE)
        }
        route(DemoConstants.DEFAULT_BUS_LOADER_ERROR_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_LOADER_ERROR_QUEUE)
        }
        
        route(DemoConstants.DEFAULT_BUS_SAVER_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_SAVER_QUEUE)
        }
        route(DemoConstants.DEFAULT_BUS_SAVER_ERROR_ROUTE) {
            queue(DemoConstants.DEFAULT_BUS_SAVER_ERROR_QUEUE)
        }
        
    }

    delay()

    service(DemoConstants.DEFAULT_BUS_SERVICE_NAME) {
        input(DemoConstants.DEFAULT_BUS_CRAWLER_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_CRAWLER_QUEUE)
        out(DemoConstants.DEFAULT_BUS_CRAWLER_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_CRAWLER_ROUTE)
        out(DemoConstants.DEFAULT_BUS_CRAWLER_SERVICE_ERROR, DemoConstants.DEFAULT_BUS_CRAWLER_ERROR_ROUTE)

        input(DemoConstants.DEFAULT_BUS_LOADER_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_LOADER_QUEUE)
        out(DemoConstants.DEFAULT_BUS_LOADER_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_LOADER_ROUTE)
        out(DemoConstants.DEFAULT_BUS_LOADER_SERVICE_ERROR, DemoConstants.DEFAULT_BUS_LOADER_ERROR_ROUTE)

        input(DemoConstants.DEFAULT_BUS_SAVER_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_SAVER_QUEUE)
        out(DemoConstants.DEFAULT_BUS_SAVER_SERVICE_INPUT, DemoConstants.DEFAULT_BUS_SAVER_ROUTE)
        out(DemoConstants.DEFAULT_BUS_SAVER_SERVICE_ERROR, DemoConstants.DEFAULT_BUS_SAVER_ERROR_ROUTE)
    }

}