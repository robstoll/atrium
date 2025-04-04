import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.kotlin-jvm")
}

description = "java api generator based on a Kotlin compiler plugin"


dependencies {
    implementation(prefixedProject("logic"))
    implementation(kotlin("compiler-embeddable"))
    implementation("com.github.tschuchortdev:kotlin-compile-testing:1.6.0")
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
