import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("ch.tutteli.gradle.plugins.junitjacoco")
}

//TODO 1.4.0 move this bit to tutteli-gradle-plugins, could be done there depending if com.github.vlsi.gradle-extensions
// is applied or not
tasks.withType<Test>().configureEach {
    testLogging {
        // Empty enum throws "Collection is empty", so we use Iterable method
        setEvents((events - TestLogEvent.FAILED) as Iterable<TestLogEvent>)
        showStackTraces = false
    }
}

//TODO switch to buildLibs once gradle supports this in build-logic/src/kotlin files (also jupiter-api above)
val jacocoToolVersion: String by lazy {
    if (rootProject.extra.has("jacocoToolVersion")) {
        rootProject.extra.get("jacocoToolVersion") as String
    } else {
      ""
    }
}

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
