package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionBigDecimalAssertions(override val value: String) : StringBasedTranslatable {
    IS_NUMERICALLY_EQUAL_TO("is numerically equal to"),
    FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL("notice, the assertion would have hold for %s"),
}
