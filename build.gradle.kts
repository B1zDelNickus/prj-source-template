import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.gson.GsonBuilder
import org.gradle.internal.impldep.com.google.common.io.Files

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.google.code.gson:gson:2.8.5")
    }
}

val gson = GsonBuilder().setPrettyPrinting().create()
data class SourceDefinition (var code:String="",var name:String=""){
    val packageName get() = code.replace("-","_")
    fun packageName(proj:Project):String{
        return "codes.spectrum.sources.$packageName.${proj.name}"
    }
    fun interpolateMap(): Map<String, Any?> {
        return mapOf(
            "SOURCE_CODE" to code,
            "SOURCE_NAME" to name
        )
    }
    fun interpolate(file:File){
        file.interpolate(interpolateMap())
    }
}
val sourceDefFile = File(rootProject.projectDir, "source.json")
var sourceDef = SourceDefinition()
if(sourceDefFile.exists()){
    sourceDef = gson.fromJson(sourceDefFile.readText(),SourceDefinition::class.java)
}

// версия может быть перекрыта при вызове параметром `publish_version`
val version = "1.0.0"
spectrumMultimodule(version) {
    commonDependency(project(":model"))
}
val checkSourceInitialized = tasks.register("check-source-initialized") {
    group = "sources"
    doFirst {
        if(sourceDef.code.isBlank()){
            throw Exception("Source not defined")
        }
    }
}

val setupSource = tasks.register("setup-source") {
    group = "sources"
    dependsOn(checkSourceInitialized)
    doFirst {
        subprojects {
            this.ensurePackage(sourceDef.packageName(this))
        }
        fun processTemplateDir(dir:File){
            for(f in dir.listFiles()){
                if(f.isDirectory){
                    processTemplateDir(f)
                }else{
                    val newName = File(f.canonicalPath.replace("\\","/").replace("/templates/","/"))
                    newName.parentFile.mkdirs()
                    newName.writeText(f.readText())
                    sourceDef.interpolate(newName)
                }
            }
        }
        processTemplateDir(File(rootProject.projectDir, "templates"))

    }
}


subprojects {
    tasks.withType<KotlinCompile>() {
        dependsOn(setupSource)
    }
}


