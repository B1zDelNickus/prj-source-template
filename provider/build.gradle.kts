val spectrum_legacy_version = property("spectrum-version") as String

dependencies {
    // TODO (Конвеер должен подтягиваться из core, убрать когда заработает)
    implementation("codes.spectrum:konveyor:0.1.8")
}