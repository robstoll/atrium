plugins {
    id("build-logic.kotlin-multiplatform")
    id("ch.tutteli.gradle.plugins.publish")
    id("build-logic.publish-to-tmp-maven-repo")
    id("build-logic.dokka")
}

tutteliPublish {
    resetLicenses("EUPL-1.2")
}
