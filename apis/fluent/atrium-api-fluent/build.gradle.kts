import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "A fluent expectation function API in en_GB with a focus on code completion"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("logic"))
                compileOnly(prefixedProject("translations-en_GB"))
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.niok)
            }
        }

        commonTest {
            dependencies {
                implementation(prefixedProject("specs"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
    }
}

tasks.withType<KotlinCompile<*>>().forEach {
    println("KotlinCompile: ${it.name} -- ${it.kotlinOptions.apiVersion} // ${it.kotlinOptions.languageVersion}")
}
tasks.withType<KotlinCompileCommon>().forEach {
    println("KotlinCompileCommon: ${it.name} -- ${it.kotlinOptions.apiVersion} // ${it.kotlinOptions.languageVersion}")
}
tasks.withType<KotlinCompilationTask<*>>().forEach {
    println("KotlinCompilationTask: ${it.name} -- ${it.compilerOptions.apiVersion.get()} // ${it.compilerOptions.languageVersion.get()}")
}


println("tasks: ${tasks.joinToString("\n- ")}")

junitjacoco {
    additionalProjectSources.addAll(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core"),
        prefixedProject("verbs"),
    )
}
