plugins {
    id("org.gradlex.build-parameters") version "1.4.3"
    id("com.github.vlsi.gradle-extensions") version "1.88"
    id("build-logic.kotlin-dsl-gradle-plugin")
}

buildParameters {
    // Other plugins can contribute parameters, so below list is not exhaustive
    enableValidation.set(false)
    pluginId("build-logic.build-params")
    bool("skipJavadoc") {
        defaultValue.set(false)
        description.set("Skip javadoc generation")
    }
    bool("failOnJavadocWarning") {
        defaultValue.set(true)
        description.set("Fail build on javadoc warnings")
    }
    bool("enableGradleMetadata") {
        defaultValue.set(false)
        description.set("Generate and publish Gradle Module Metadata")
    }
    bool("useGpgCmd") {
        defaultValue.set(false)
        description.set("By default use Java implementation to sign artifacts. When useGpgCmd=true, then gpg command line tool is used for signing artifacts")
    }
    bool("werror") {
        defaultValue.set(true)
        description.set("Treat javac, javadoc, kotlinc warnings as errors")
    }
    integer("defaultJdkVersion") {
        defaultValue.set(11)
        mandatory.set(true)
        description.set("Default Java version for source and target compatibility")
    }
}
