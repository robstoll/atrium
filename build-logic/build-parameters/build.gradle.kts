plugins {
    id("org.gradlex.build-parameters") version "1.4.3"
    id("com.github.vlsi.gradle-extensions") version "1.88"
    id("build-logic.kotlin-dsl-gradle-plugin")
}

buildParameters {
    pluginId("build-logic.build-params")

    // Other plugins can contribute parameters, so below list is not exhaustive, hencew we disable the validation
    enableValidation.set(false)

    val defaultJdkVersion = 11
    integer("defaultJdkVersion") {
        defaultValue.set(defaultJdkVersion)
        mandatory.set(true)
        description.set("Default Java version for source and target compatibility")
    }

    group("kotlin") {
        bool("werror") {
            defaultValue.set(true)
            description.set("Treat kotlinc warnings as errors")
        }
    }


    group("java") {
        integer("version") {
            fromEnvironment()
            defaultValue.set(defaultJdkVersion)
            description.set("Java version used for java.toolchain")
        }
        bool("werror") {
            defaultValue.set(true)
            description.set("Treat javac, javadoc, warnings as errors")
        }
    }

}
