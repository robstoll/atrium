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
                api("com.github.ajalt.mordant:mordant:3.0.2")
            }
        }
        commonTest {
            dependencies {
                implementation(prefixedProject("api-infix"))
                implementation(prefixedProject("specs"))
            }
        }
        jvmMain {
            dependencies {
                implementation("org.opentest4j:opentest4j:1.3.0")
            }
        }
    }
}

ifIsPublishing {
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
}


createGenerateCoreTask(
    includingTarget("jvm"),
    includingTarget(
        "common",
        //TODO 1.3.0 enable once transitioned to proofs
//        "/creating/charsequence/contains/creators" to { _ ->
//            "<T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerStepLogic<T, S>" to
//                "entryPointStepLogic.container.getImpl"
//        },
//        "/creating/iterable/contains/creators" to { path ->
//            when (val fileNameAsString = path.fileName.toString()) {
//                "IterableLikeContainsInAnyOrderProofs.kt" ->
//                    "<E, T : Any> IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>" to
//                        "entryPointStepLogic.container.getImpl"
//
//                "IterableLikeContainsProofs.kt" ->
//                    "<E, T : Any, S : IterableLikeContains.SearchBehaviour> IterableLikeContains.EntryPointStepLogic<E, T, S>" to
//                        "container.getImpl"
//
//                else -> throw IllegalStateException("define a configuration for this new file this new file $fileNameAsString in build.gradle.kts of atrium-logic for createGenerateLogicTaskForCommon")
//            }
//        },
//        "/creating/maplike/contains/creators" to { path ->
//
//            when (val fileNameAsString = path.fileName.toString()) {
//                "MapLikeContainsProofs.kt" ->
//                    "<K, V, T : Any, S : MapLikeContains.SearchBehaviour> MapLikeContains.EntryPointStepLogic<K, V, T, S>" to
//                        "container.getImpl"
//
//                else -> throw IllegalStateException("define a configuration for this new file this new file $fileNameAsString in build.gradle.kts of atrium-logic for createGenerateLogicTaskForCommon")
//            }
//        }
    )
)
