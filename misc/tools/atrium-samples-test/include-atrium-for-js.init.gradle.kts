val jsSamples = setOf("atrium-js-kotlin-test-mocha", "atrium-multiplatform-kotlin-test")

gradle.settingsEvaluated {
    if (rootProject.name in jsSamples) {
        // for whatever reason, we need to include atrium in the settings of those projects so that kotlinNpmInstall
        // works
        includeBuild("../../../")
    }
}
