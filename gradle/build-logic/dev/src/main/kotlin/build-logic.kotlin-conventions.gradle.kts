import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.junit-jacoco-conventions")
    // this plugin sets inter alia toolchain and source/targetCompatibility
    // but also applies common plugins such as gradle-convention, build-params
    id("build-logic.java")
}

tasks.configureEach<KotlinCompile> {
    kotlinOptions {
        jvmTarget = buildParameters.defaultJdkVersion.toString()
    }
}

tasks.configureEach<KotlinCompilationTask<*>> {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        // suppress warnings about kotlin 1.4 being deprecated
        freeCompilerArgs.add("-Xsuppress-version-warnings")
        // suppress warnings about expect/actual being an experimental feature
        freeCompilerArgs.add("-Xexpect-actual-classes")

        val kotlinVersion = KotlinVersion.fromVersion(buildParameters.kotlin.version)
        languageVersion.set(kotlinVersion)
        apiVersion.set(kotlinVersion)
    }
}
