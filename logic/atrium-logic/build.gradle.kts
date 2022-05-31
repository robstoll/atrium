description = "The domain logic of Atrium"

val niokVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("core"))
                // it is up to the consumer which atrium-translations module is used at runtime
                compileOnly(prefixedProject("translations-en_GB"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("ch.tutteli.niok:niok:$niokVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(prefixedProject("api-fluent-en_GB"))
                implementation(prefixedProject("specs-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(prefixedProject("specs-jvm"))
            }
        }
    }
}

createGenerateLogicTask(
    includingTarget("jvm"),
    includingTarget("common",
        "/creating/charsequence/contains/creators" to { _ ->
            "<T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerStepLogic<T, S>" to
                "entryPointStepLogic.container.getImpl"
        },
        "/creating/iterable/contains/creators" to { path ->
            when (val fileNameAsString = path.fileName.toString()) {
                "IterableLikeContainsInAnyOrderAssertions.kt" ->
                    "<E, T : Any> IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>" to
                        "entryPointStepLogic.container.getImpl"
                "IterableLikeContainsAssertions.kt" ->
                    "<E, T : Any, S : IterableLikeContains.SearchBehaviour> IterableLikeContains.EntryPointStepLogic<E, T, S>" to
                        "container.getImpl"
                else -> throw IllegalStateException("define a configuration for this new file this new file $fileNameAsString in build.gradle.kts of atrium-logic for createGenerateLogicTaskForCommon")
            }
        },
        "/creating/maplike/contains/creators" to { path ->

            when (val fileNameAsString = path.fileName.toString()) {
                "MapLikeContainsAssertions.kt" ->
                    "<K, V, T : Any, S : MapLikeContains.SearchBehaviour> MapLikeContains.EntryPointStepLogic<K, V, T, S>" to
                        "container.getImpl"
                else -> throw IllegalStateException("define a configuration for this new file this new file $fileNameAsString in build.gradle.kts of atrium-logic for createGenerateLogicTaskForCommon")
            }
        }
    )
)
