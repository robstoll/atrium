plugins {
    id("build-logic.kotlin-multiplatform")
    id("ch.tutteli.gradle.plugins.publish") apply isPublishing()
    id("build-logic.publish-to-tmp-maven-repo")
    id("build-logic.dokka")
}

ifIsPublishing {
    tutteliPublish {
        resetLicenses("EUPL-1.2")
    }
}
