package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

//TODO #280 add doc
interface BaseExpectConfig {
    val description: Translatable
    val representation: Any?
    val reporter: Reporter
}

//TODO #280 add doc
interface RootExpectConfig : BaseExpectConfig {
    companion object {
        fun create(description: Translatable, representation: Any?, reporter: Reporter): RootExpectConfig =
            ExpectConfig(description, representation, reporter)
    }
}

//TODO #280 add doc
interface FeatureExpectConfig : BaseExpectConfig {
    companion object {
        fun create(description: Translatable, representation: Any?, reporter: Reporter): FeatureExpectConfig =
            ExpectConfig(description, representation, reporter)
    }
}

private data class ExpectConfig(
    override val description: Translatable,
    override val representation: Any?,
    override val reporter: Reporter
) : FeatureExpectConfig,
    RootExpectConfig
