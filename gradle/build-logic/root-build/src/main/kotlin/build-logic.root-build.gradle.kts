import ch.tutteli.gradle.plugins.dokka.DokkaPluginExtension
import ch.tutteli.gradle.plugins.dokka.GhPages
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import java.io.IOException
import java.net.URL


plugins {
    id("build-logic.gradle-conventions")
}

val rootProject = this

ifIsPublishing {
    println("""
        |Publishing activated via env PUB=true, going to add tutteli-publish plugins and co. in sub-projects
        |Also applying tutteli-dokka in root project for dokkaHtmlMultiModule
        """.trimMargin())
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "ch.tutteli.gradle.plugins.dokka")

    the<DokkaPluginExtension>().run {
        writeTo.set(GhPages)

        val modulesNotInGhPages = listOf(
            // internal modules are not of interest
            "specs",
            "verbs-internal",
            // a user will most likely never look up translations, only clutter the search
            "translations-de_CH", "translations-en_GB"
        )
        modulesNotInGhPages.forEach { projectName ->
            prefixedProject(projectName).afterEvaluate {
                val subproject = this
                rootProject.tasks.configureEach<DokkaMultiModuleTask> {
                    dependsOn(subproject.tasks.named("cleanDokkaHtmlPartial"))
                }
            }
        }
        tasks.configureEach<DokkaMultiModuleTask> {
            moduleName.set("Atrium")
            configurePlugins()
        }

        gradle.taskGraph.whenReady {
            if (hasTask(":dokkaHtmlMultiModule")) {
                modulesNotInGhPages.forEach { projectName ->
                    prefixedProject(projectName).tasks.configureEach<DokkaTaskPartial> {
                        enabled = false
                    }
                }
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

