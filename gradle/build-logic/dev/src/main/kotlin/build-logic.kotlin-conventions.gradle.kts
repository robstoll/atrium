import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id("build-logic.gradle-conventions")
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
