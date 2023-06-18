import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.build-params")
    id("build-logic.junit-jacoco-conventions")
    // this plugin sets inter alia toolchain and source/targetCompatibility
    id("build-logic.java")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = buildParameters.defaultJdkVersion.toString()
    }
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    compilerOptions {
        // suppress warnings about kotlin 1.4 beeing deprecated
        freeCompilerArgs.add("-Xsuppress-version-warnings")
    }
}
