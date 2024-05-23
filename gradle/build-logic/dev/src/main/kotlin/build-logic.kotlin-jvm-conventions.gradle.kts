import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.kotlin-conventions")
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
