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
    IS_NOT_THROWING_1("wirft"),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWING_2("keine Exception bei Aufruf"),

    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFunLikeExpectation.NO_EXCEPTION_OCCURRED")
    )
    NO_EXCEPTION_OCCURRED("❗❗ keine Exception wurde geworfen"),

    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFunLikeExpectation.THROWN_EXCEPTION_WHEN_CALLED")
    )
    THROWN_EXCEPTION_WHEN_CALLED("geworfene Exception bei Aufruf"),

    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFunLikeExpectation.THREW")
    )
    THREW("❗❗ warf %s")
}
