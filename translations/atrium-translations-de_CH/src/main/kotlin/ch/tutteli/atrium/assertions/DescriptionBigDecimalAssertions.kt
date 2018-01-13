package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
enum class DescriptionBigDecimalAssertions(override val value: String) : StringBasedTranslatable {
    FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL("beachte, wäre %s verwendet worden, würde die Behauptung stimmen."),
    IS_NUMERICALLY_EQUAL_TO("ist numerisch gleich wie"),
    IS_NOT_NUMERICALLY_EQUAL_TO("is nicht numerisch gleich wie"),
}
