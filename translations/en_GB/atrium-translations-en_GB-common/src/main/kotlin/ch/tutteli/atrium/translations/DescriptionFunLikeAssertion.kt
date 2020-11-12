package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionFunLikeAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Will be removed with 1.0.0")
    IS_NOT_THROWING_1("does not"),
    @Deprecated("Will be removed with 1.0.0")
    IS_NOT_THROWING_2("throw when invoked"),
    NO_EXCEPTION_OCCURRED("❗❗ no exception occurred"),
    THROWN_EXCEPTION_WHEN_CALLED("thrown exception when called"),
    THREW("❗❗ threw %s")
}
