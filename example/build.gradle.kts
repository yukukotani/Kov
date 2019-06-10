import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
}
repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    compile(rootProject)
}

tasks.withType<Kotlin2JsCompile> {
    kotlinOptions {
        moduleKind = "umd"
    }
}

task<Copy>("assembleJsLib") {
    configurations.compile.get().resolve().forEach { file: File ->
        from(zipTree(file.absolutePath), {
            includeEmptyDirs = false
            include { fileTreeElement ->
                val path = fileTreeElement.path
                (path.endsWith(".js") || path.endsWith(".js.map")) && (path.startsWith("META-INF/resources/") ||
                    !path.startsWith("META-INF/"))
            }
        })
    }
    from(tasks.withType<ProcessResources>().map { it.destinationDir })
    into("$buildDir/js")

    dependsOn("classes")
}

tasks.build {
    finalizedBy("assembleJsLib")
}
