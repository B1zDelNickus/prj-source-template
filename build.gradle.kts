// версия может быть перекрыта при вызове параметром `publish_version`
val version = "1.0.0"
spectrumMultimodule(version) {
    commonDependency(project(":model"))
}


tasks.register("set-source-name") {
    group = "sources"
    doFirst {
        val name = (rootProject.findProperty("source-name") ?: rootProject.name).toString()
        val packageName = name.replace("-", "_")
        subprojects {
            this.ensurePackage("codes.spectrum.sources.$packageName.$name")
        }
    }
}


