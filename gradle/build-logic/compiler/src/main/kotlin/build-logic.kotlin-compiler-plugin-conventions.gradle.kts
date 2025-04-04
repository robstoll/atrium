import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    //TODO 1.3.0 remove again and use META-INF instead if possible
    kotlin("kapt")
    id("build-logic.kotlin-jvm-conventions")
    id("ch.tutteli.gradle.plugins.spek")

}

dependencies {
    compileOnly(kotlin("compiler-embeddable"))

    kapt("com.google.auto.service:auto-service:1.1.1")
    compileOnly("com.google.auto.service:auto-service:1.1.1")

    testImplementation(kotlin("compiler-embeddable"))
    testImplementation("com.github.tschuchortdev:kotlin-compile-testing:1.6.0")
}

val kotlinVersionOfCompileTesting = "1.9.24"
configurations.all {
    if (this.name.startsWith("test")) {
        resolutionStrategy {
            // needs to be in sync with kotlin-compile-testing
            force("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersionOfCompileTesting")
            force("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersionOfCompileTesting")
        }
    }
}

val kotlinVersion = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.fromVersion(
    kotlinVersionOfCompileTesting.substring(0, kotlinVersionOfCompileTesting.indexOf('.', startIndex = 2))
)
tasks.withType<KotlinCompile>().configureEach {
    val task = this
    compilerOptions {
        optIn.add("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
        optIn.add("org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI")

        if (task.name.lowercase().contains("test")) {
            apiVersion.set(kotlinVersion)
            languageVersion.set(kotlinVersion)
        }
    }
}

project.afterEvaluate {
    tasks.withType<Test>().configureEach {
        useJUnitPlatform {
            includeEngines = setOf("junit-jupiter")
        }
    }
}
