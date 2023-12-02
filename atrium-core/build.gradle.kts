import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Core module of Atrium, containing all contracts/interfaces and default implementations"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kbox)
            }
        }
        commonTest {
            dependencies {
                implementation(prefixedProject("api-infix"))
                implementation(prefixedProject("specs"))
            }
        }
    }
}

// TODO 1.3.0 some bug in dokka, maybe fixed in a never version? We should be able to suppress jvmMain for js and common
// see https://github.com/Kotlin/dokka/issues/3209
project.afterEvaluate {
    tasks.named<DokkaTask>("dokkaHtmlJs") {
        dokkaSourceSets.named("jvmMain") {
            suppress.set(false)
        }
    }
    tasks.named<DokkaTask>("dokkaHtmlKotlinMultiplatform") {
        dokkaSourceSets.named("jvmMain") {
            suppress.set(false)
        }
    }
}

// comment out to see workaround working
//tasks.named("test") {
//    dependsOn(tasks.named("jsNodeTest"))
//}
