package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    TO_BE_LESS_THAN("to be less than"),

    /** @since 0.18.0 */
    TO_BE_LESS_THAN_OR_EQUAL_TO("to be less than or equal to"),

    /** @since 0.18.0 */
    NOT_TO_BE_GREATER_THAN("not to be greater than"),

    /** @since 0.18.0 */
    TO_BE_EQUAL_COMPARING_TO("to be equal comparing to"),

    /** @since 0.18.0 */
    TO_BE_GREATER_THAN_OR_EQUAL_TO("to be greater than or equal to"),

    /** @since 0.18.0 */
    NOT_TO_BE_LESS_THAN("not to be less than"),

    /** @since 0.18.0 */
    TO_BE_GREATER_THAN("to be greater than"),
}
