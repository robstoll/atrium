import ch.tutteli.gradle.plugins.dokka.DokkaPluginExtension
import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    id("build-logic.gradle-conventions")
    id("ch.tutteli.gradle.plugins.dokka")
}

val kdocDir = rootProject.projectDir.resolve("misc/kdoc")

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)
    }
}

tasks.withType<AbstractDokkaLeafTask>().configureEach {
    dokkaSourceSets.configureEach {
        jdkVersion.set(buildParameters.defaultJdkVersion)
        perPackageOption {
            matchingRegex.set("io.mockk")
            suppress.set(true)
        }
        includes.from(kdocDir.resolve("packages.md"))

        //TODO 1.1.0 remove with update to tutteli-gradle 4.10.0
        externalDocumentationLink {
            val extension = rootProject.the<DokkaPluginExtension>()
            url.set(extension.githubUser.flatMap { githubUser ->
                extension.modeSimple.map { usesSimpleDocs ->
                    if (usesSimpleDocs) {
                        URL("https://$githubUser.github.io/${rootProject.name}/kdoc/${rootProject.name}/")
                    } else {
                        URL("https://$githubUser.github.io/${rootProject.name}/${rootProject.version}/kdoc/")
                    }
                }
            })
        }
    }
    configurePlugins()
}
