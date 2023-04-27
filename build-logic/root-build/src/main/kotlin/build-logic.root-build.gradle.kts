plugins {
    id("build-logic.build-params")
    id("org.jetbrains.dokka")
    id("ch.tutteli.gradle.plugins.dokka")
}

tutteliDokka {
    modeSimple.set(false)
    githubUser.set("test")
}
