plugins {
    id("build-logic.published-java-library")
}

description = "A fluent expectation function API for java with a focus on code completion"

dependencies {
    api(prefixedProject("logic"))
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}


junitjacoco {
    additionalProjectSources.addAll(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core"),
        prefixedProject("verbs"),
    )
}
