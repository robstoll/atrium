package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    TO_BE_LESS_THAN("ist weniger als"),

    /** @since 0.18.0 */
    TO_BE_LESS_THAN_OR_EQUAL_TO("ist weniger als oder gleich wie"),

    /** @since 0.18.0 */
    NOT_TO_BE_GREATER_THAN("ist nicht grösser als"),

    /** @since 0.18.0 */
    TO_BE_EQUAL_COMPARING_TO("ist gleich wie"),

    /** @since 0.18.0 */
    TO_BE_GREATER_THAN_OR_EQUAL_TO("ist grösser als oder gleich wie"),

    /** @since 0.18.0 */
    NOT_TO_BE_LESS_THAN("ist nicht weniger als"),

    /** @since 0.18.0 */
    TO_BE_GREATER_THAN("ist grösser als"),
}
