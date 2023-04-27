import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.findByType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

val jacocoToolVersion: String by rootProject.extra

plugins.withId("jacoco") {
    configure<JacocoPluginExtension> {
        if (rootProject.name != "gradle-kotlin-dsl-accessors") {
            toolVersion = jacocoToolVersion
        }
    }

    val jacocoAdditionalExtraName = "jacocoAdditional"
    tasks.withType<JacocoReport>()
        .matching { it.name == "jacocoTestReport" }
        .configureEach {
            if (project.extra.has(jacocoAdditionalExtraName)) {
                val additional = project.extra.get(jacocoAdditionalExtraName) as List<*>
                additional.forEach { p ->
                    val otherProject = p as Project
                    val kotlin = otherProject.extensions
                        .findByType<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>()
                    kotlin?.sourceSets?.filterNot { it.name.contains("Test") }?.forEach {
                        additionalSourceDirs(it.kotlin.sourceDirectories)
                    }
                }
            }

            reports {
                html.required.set(true)
            }
        }
}
