package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionBigDecimalAssertions(override val value: String) : StringBasedTranslatable {
    IS_NUMERICALLY_EQUAL_TO("ist numerisch gleich wie:"),
    FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL("beachte, wäre %s verwendet worden, würde die Behauptung stimmen."),
}
