import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// версия может быть перекрыта при вызове параметром `publish_version`
val version = "1.0.0"
spectrumMultimodule(version) {
    commonDependency(project(":model"))
}
val checkSourceInitialized = tasks.register("check-source-initialized") {
    group = "sources"
    doFirst {
        val props = File(rootProject.projectDir, "gradle.properties")
        if (!props.exists()) throw Exception("File gradle.properties not exists")
        if (props.readText().contains("~")) throw Exception("File gradle.properties not fully defined")
    }
}

val setupSource = tasks.register("setup-source") {
    group = "sources"
    dependsOn(checkSourceInitialized)
    doFirst {
        val sourceName = rootProject.ensureProperty("source-name")
        val packageName = sourceName.replace("-", "_")
        subprojects {
            this.ensurePackage("codes.spectrum.sources.$packageName.$name")
        }
    }
}

subprojects {
    tasks.withType<KotlinCompile>() {
        dependsOn(checkSourceInitialized)
    }
}


