package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionFunLikeProof(override val value: String) : StringBasedTranslatable {
    /** @since 1.2.0 (but was since 0.18.0 in atrium-translations DescriptionFunLikeExpectation) */
    NO_EXCEPTION_OCCURRED("❗❗ no exception occurred"),

    /** @since 1.2.0 (but was since 0.18.0 in atrium-translations DescriptionFunLikeExpectation) */
    THROWN_EXCEPTION_WHEN_CALLED("thrown exception when called"),

    /** @since 1.2.0 (but was since 0.18.0 in atrium-translations DescriptionFunLikeExpectation) */
    THREW("❗❗ threw %s")
}
