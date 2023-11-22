import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    id("ch.tutteli.gradle.plugins.junitjacoco")
}

dependencies {
    // used to run the samples
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}

//TODO switch to buildLibs once gradle supports this in build-logic/src/kotlin files (also jupiter-api above)
val jacocoToolVersion: String by rootProject.extra

plugins.withId("jacoco") {

    configure<JacocoPluginExtension> {
        if (rootProject.name != "gradle-kotlin-dsl-accessors") {
            toolVersion = jacocoToolVersion
        }
    }

    tasks.withType<JacocoReport>()
        .matching { it.name == "jacocoTestReport" }
        .configureEach {
            reports {
                html.required.set(true)
            }
        }
}
