import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
    alias(buildLibs.plugins.build.parameters)
}

buildParameters {
    pluginId("build-logic.build-params")

    // Other plugins can contribute parameters, so below list is not exhaustive, hence we disable the validation
    enableValidation.set(false)

    val defaultJdkVersion = 11
    integer("defaultJdkVersion") {
        defaultValue.set(defaultJdkVersion)
        mandatory.set(true)
        description.set("Default Java version for source and target compatibility")
    }

    group("kotlin") {
        string("version") {
            fromEnvironment()
            @Suppress("DEPRECATION" /* we support kotlin_1_4 on purpose */)
            defaultValue.set(KotlinVersion.KOTLIN_1_4.version)
            description.set("kotlin version used for apiVersion and languageVersion")
        }
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
