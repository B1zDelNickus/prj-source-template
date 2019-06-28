val spectrum_legacy_version = property("spectrum-version") as String

dependencies {
    implementation("codes.spectrum:spectrum-amqp:$spectrum_legacy_version")
    implementation("codes.spectrum.bus:bus-core-lib:$spectrum_legacy_version")
    implementation("codes.spectrum.sources.core:spectrum-core-service:${ensureProperty("source-core-version", "0.5.0-dev-SNAPSHOT")}")
    implementation("codes.spectrum.sources.core:spectrum-core-bus:${ensureProperty("source-core-version", "0.5.0-dev-SNAPSHOT")}")
    implementation( project (":transport"))
}