//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionFunLikeAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWING_1("does not"),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWING_2("throw when invoked"),

    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFunLikeExpectation.NO_EXCEPTION_OCCURRED")
    )
    NO_EXCEPTION_OCCURRED("❗❗ no exception occurred"),

    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFunLikeExpectation.THROWN_EXCEPTION_WHEN_CALLED")
    )
    THROWN_EXCEPTION_WHEN_CALLED("thrown exception when called"),

    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFunLikeExpectation.THREW")
    )
    THREW("❗❗ threw %s")
}
