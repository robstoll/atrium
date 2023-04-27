import org.gradle.kotlin.dsl.getByType

plugins {
    id("build-logic.kotlin-multiplatform")
    id("build-logic.publish-to-tmp-maven-repo")
    id("build-logic.dokka")
    id("ch.tutteli.gradle.plugins.publish")
}

tutteliPublish {
    resetLicenses("EUPL-1.2")
}
