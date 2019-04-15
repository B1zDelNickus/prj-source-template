import sources.sourceProject
// версия может быть перекрыта при вызове параметром `publish_version`
sourceProject(ensureProperty("publish-version","1.0.0"))

val istemplate = rootProject.name == "source"

if(istemplate){
    fun forceCleanDirectory(dir:File){
        for(f in dir.listFiles()){
            if(f.isDirectory){
                forceCleanDirectory(f)
            }else{
                f.delete()
            }
        }
        dir.delete()
    }
    val reset_template = tasks.register("reset-template"){
        group = "sources"
        doFirst{
            File("./source.json").writeText("""{
  "code":"",
  "name":""
}""")
            File("./README.md").writeText(File("./buildSrc/src/main/kotlin/sources/README.md").readText())
            for(d in arrayOf("test","model", "provider", "client", "service", "db", "transport", "agent")){
                val dir = File("./${d}")
                if(dir.exists()){
                    forceCleanDirectory(dir)
                    dir.mkdirs()
                    File(dir,"build.gradle.kts").createNewFile()
                }
            }
        }
    }
}