plugins {
    id("build-logic.kotlin-compiler-plugin-gradle-conventions")
}

gradlePlugin {
    plugins {
        create("javaApiGenerator") {
            id = "ch.tutteli.atrium.tools.generator.java"
            implementationClass = "ch.tutteli.atrium.tools.generators.java.gradle.JavaApiGeneratorSubplugin"
        }
    }
}
