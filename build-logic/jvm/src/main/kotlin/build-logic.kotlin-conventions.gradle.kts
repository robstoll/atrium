import gradle.kotlin.dsl.accessors._2ce093fcac2be43e80ba2d72b4de454d.buildParameters
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.build-params")
    id("build-logic.jacoco-conventions")
    // Even though Kotlin does not use Java, we need to set javaCompile.targetCompatibility
    // to avoid Kotlin compilation failures
    id("build-logic.java")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        kotlinOptions.jvmTarget = buildParameters.defaultJdkVersion.toString()
        // TODO 2.0.0 remove if we use kotlin 1.7.x should no longer be necessary
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}
