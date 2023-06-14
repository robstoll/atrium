plugins {
    id("build-logic.kotlin-multiplatform")
    //TODO 1.1.0 build-logic causes that build depends on javadoc and in turn dokka is part of the build this is a major slow-down
    // make sure javadoc jar is only generated during publish
//    id("build-logic.publish-to-tmp-maven-repo")
    id("build-logic.dokka")
    id("ch.tutteli.gradle.plugins.publish")
}

tutteliPublish {
    resetLicenses("EUPL-1.2")
}
