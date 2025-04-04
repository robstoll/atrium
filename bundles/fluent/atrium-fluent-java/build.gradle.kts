plugins {
    id("build-logic.published-java-library")
}

description = "Convenience module which depends on atrium-api-fluent-java"

dependencies {
    api(prefixedProject("api-fluent-java"))
    api(prefixedProject("translations-en_GB"))
}

junitjacoco {
    additionalProjectSources.addAll(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core")
    )
}
