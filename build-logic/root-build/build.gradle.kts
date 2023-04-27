import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import java.io.IOException
import java.net.URL

plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.buildParameters)
    api(projects.jvm)
    api("ch.tutteli.gradle.plugins.dokka:ch.tutteli.gradle.plugins.dokka.gradle.plugin:4.9.0")
    api("org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:1.8.10")
    api("org.jetbrains.dokka:dokka-base:1.8.10")
}

// for whatever reason, this is done in the root project and not in the individual subprojects which actually have a
// JS target.
tasks.withType<KotlinNpmInstallTask>().configureEach {
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
