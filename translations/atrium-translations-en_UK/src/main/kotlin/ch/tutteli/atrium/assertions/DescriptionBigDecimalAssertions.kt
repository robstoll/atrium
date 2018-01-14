package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
enum class DescriptionBigDecimalAssertions(override val value: String) : StringBasedTranslatable {
    FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL("notice, if you used %s then the assertion would have hold."),
    IS_NUMERICALLY_EQUAL_TO("is numerically equal to"),
    IS_NOT_NUMERICALLY_EQUAL_TO("is not numerically equal to"),
}
