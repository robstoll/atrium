//TODO remove with 0.14.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.Translatable

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@Experimental
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
@Deprecated("will be removed with 0.14.0")
annotation class ExperimentalExpectConfig

@ExperimentalExpectConfig
@Deprecated("will be removed with 0.14.0")
interface BaseExpectConfig {
    val description: Translatable
    val representation: Any?
}

@ExperimentalExpectConfig
@Deprecated("will be removed with 0.14.0")
interface RootExpectConfig : BaseExpectConfig {
    companion object {
        fun create(description: Translatable, representation: Any?): RootExpectConfig =
            ExpectConfig(description, representation)
    }
}

@ExperimentalExpectConfig
@Deprecated("will be removed with 0.14.0")
interface FeatureExpectConfig : BaseExpectConfig {
    companion object {
        fun create(description: Translatable, representation: Any?): FeatureExpectConfig =
            ExpectConfig(description, representation)
    }
}

@ExperimentalExpectConfig
@Deprecated("will be removed with 0.14.0")
private data class ExpectConfig(
    override val description: Translatable,
    override val representation: Any?
) : FeatureExpectConfig,
    RootExpectConfig
