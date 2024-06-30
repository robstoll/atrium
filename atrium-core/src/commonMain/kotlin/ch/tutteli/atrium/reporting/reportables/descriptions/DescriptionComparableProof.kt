package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Comparable].
 */
enum class DescriptionComparableProof(override val string: String) : Description {
    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    TO_BE_LESS_THAN("to be less than"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    TO_BE_LESS_THAN_OR_EQUAL_TO("to be less than or equal to"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    NOT_TO_BE_GREATER_THAN("not to be greater than"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    TO_BE_EQUAL_COMPARING_TO("to be equal comparing to"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    TO_BE_GREATER_THAN_OR_EQUAL_TO("to be greater than or equal to"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    NOT_TO_BE_LESS_THAN("not to be less than"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionComparableExpectation) */
    TO_BE_GREATER_THAN("to be greater than"),
}
