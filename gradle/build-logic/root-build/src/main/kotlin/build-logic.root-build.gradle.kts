import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import java.io.IOException
import java.net.URL


plugins {
    id("build-logic.gradle-conventions")
    id("org.jetbrains.dokka")
    id("ch.tutteli.gradle.plugins.dokka")
}

tutteliDokka {
    modeSimple.set(false)
}

tasks.configureEach<DokkaMultiModuleTask> {
    moduleName.set("Atrium")
    configurePlugins()

    // we want to be sure that we don't include those projects in dokkaHtmlMultiModule
    dependsOn(prefixedProject("specs").tasks.getByName("cleanDokkaHtmlPartial"))
    dependsOn(prefixedProject("verbs-internal").tasks.getByName("cleanDokkaHtmlPartial"))
}

gradle.taskGraph.whenReady {
    if (hasTask(":dokkaHtmlMultiModule")) {
        listOf("specs", "verbs-internal").forEach { projectName ->
            prefixedProject(projectName)
                .tasks.configureEach<DokkaTaskPartial> {
                    enabled = false
                }
        }
    }
}


// for whatever reason, this is needs to be configured in the root project and not in the individual subprojects
// which actually have a JS target.
tasks.configureEach<KotlinNpmInstallTask> {
    doFirst {
        val isOffline = try {
            val url = URL("https://www.google.com")
            val connection = url.openConnection()
            connection.connect()
            false
        } catch (e: IOException) {
            logger.warn("could not connect to www.google.com (got $e) -- going to use --offline to resolve npm dependencies")
            true
        }
        if (isOffline) {
            args.add("--offline")
        }
    }
}

