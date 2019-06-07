package codes.spectrum.sources.demo.agent

import codes.spectrum.sources.core.Severity
import codes.spectrum.sources.core.SourceDefinition
import codes.spectrum.sources.core.SourceState
import codes.spectrum.sources.core.rest.StateInfo
import codes.spectrum.sources.core.rest.setupCors
import codes.spectrum.sources.core.rest.setupNegotiation
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.util.pipeline.PipelineContext


val module: Application.() -> Unit = {
    setupNegotiation()
    setupCors()

    routing {
        route(SourceDefinition.Instance.agentApiRoot) {
            route("crawler") {
                handle {
                    runWithStateInfo { BalanceGksCrawlerRest.Instanse.execute(this) }
                }
            }
            route("loader") {
                handle {
                    runWithStateInfo { BalanceGksLoaderRest.Instanse.execute(this) }
                }
            }
            route("saver") {
                handle {
                    runWithStateInfo { BalanceGksSaverRest.Instanse.execute(this) }
                }
            }
        }
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.runWithStateInfo(sourceCall: suspend PipelineContext<Unit, ApplicationCall>.() -> SourceState) {
    StateInfo.Instance.sourceCalls.incrementAndGet()
    try {
        val status = sourceCall()
        if (status.severity == Severity.ERROR) {
            StateInfo.Instance.sourceErrors.incrementAndGet()
        }
    } catch (e: Throwable) {
        StateInfo.Instance.sourceErrors.incrementAndGet()
        throw e
    }
}