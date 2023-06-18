import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import java.io.IOException
import java.net.URL

plugins {
    id("build-logic.build-params")
    id("org.jetbrains.dokka")
    id("ch.tutteli.gradle.plugins.dokka")
}

tutteliDokka {
    modeSimple.set(false)
}


// for whatever reason, this is needs to be configured in the root project and not in the individual subprojects
// which actually have a JS target.
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

