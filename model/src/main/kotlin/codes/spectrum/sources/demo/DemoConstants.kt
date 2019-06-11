package codes.spectrum.sources.demo

object DemoConstants {
    const val SOURCE_ID = "SOURCE_ID"

    const val EXCHANGE_NAME = "$SOURCE_ID-exchange"
    const val DEFAULT_BUS_SERVICE_NAME = "$SOURCE_ID-agent-service"

    const val DEFAULT_BUS_CRAWLER_ROUTE = "$SOURCE_ID.crawler"
    const val DEFAULT_BUS_CRAWLER_QUEUE = "$SOURCE_ID-crawler"
    const val DEFAULT_BUS_CRAWLER_ERROR_ROUTE = "$SOURCE_ID.crawler.error"
    const val DEFAULT_BUS_CRAWLER_ERROR_QUEUE = "$SOURCE_ID-crawler-error"
    const val DEFAULT_BUS_CRAWLER_SERVICE_INPUT = "$SOURCE_ID-crawler-input"
    const val DEFAULT_BUS_CRAWLER_SERVICE_ERROR = "$SOURCE_ID-crawler-error"

    const val DEFAULT_BUS_LOADER_ROUTE = "$SOURCE_ID.loader"
    const val DEFAULT_BUS_LOADER_QUEUE = "$SOURCE_ID-loader"
    const val DEFAULT_BUS_LOADER_ERROR_ROUTE = "$SOURCE_ID.loader.error"
    const val DEFAULT_BUS_LOADER_ERROR_QUEUE = "$SOURCE_ID-loader-error"
    const val DEFAULT_BUS_LOADER_SERVICE_INPUT = "$SOURCE_ID-loader-input"
    const val DEFAULT_BUS_LOADER_SERVICE_ERROR = "$SOURCE_ID-loader-error"

    const val DEFAULT_BUS_SAVER_ROUTE = "$SOURCE_ID.saver"
    const val DEFAULT_BUS_SAVER_QUEUE = "$SOURCE_ID-saver"
    const val DEFAULT_BUS_SAVER_ERROR_ROUTE = "$SOURCE_ID.saver.error"
    const val DEFAULT_BUS_SAVER_ERROR_QUEUE = "$SOURCE_ID-saver-error"
    const val DEFAULT_BUS_SAVER_SERVICE_INPUT = "$SOURCE_ID-saver-input"
    const val DEFAULT_BUS_SAVER_SERVICE_ERROR = "$SOURCE_ID-saver-error"

}