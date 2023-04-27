import com.github.vlsi.gradle.dsl.configureEach
import com.github.vlsi.gradle.properties.dsl.props
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlinmpp.configureLanguageSettings

plugins {
    id("java-library")
    id("build-logic.build-params")
    id("build-logic.java")
    kotlin("jvm")
    id("build-logic.kotlin-conventions")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

val String.v: String get() = rootProject.extra["$this.version"] as String

kotlin {
    // Require explicit access modifiers and require explicit types for public APIs.
    // See https://kotlinlang.org/docs/whatsnew14.html#explicit-api-mode-for-library-authors
    if (props.bool("kotlin.explicitApi", default = true)) {
        explicitApi()
    }
    sourceSets {
        configureLanguageSettings(project)
    }
}

tasks.configureEach<KotlinCompile> {
    kotlinOptions {
        jvmTarget = buildParameters.defaultJdkVersion.toString()
    }
}
