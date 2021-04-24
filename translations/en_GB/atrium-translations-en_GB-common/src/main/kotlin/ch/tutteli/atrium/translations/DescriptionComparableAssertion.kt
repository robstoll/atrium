package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN("is less than"),

    /** @since 0.13.0 */
    IS_LESS_THAN_OR_EQUAL("is less than or equal to"),
    /** @since 0.17.0 */
    IS_NOT_GREATER_THAN("is not greater than"),

    //TODO use TO_BE_EQUAL_TO with 0.18.0
    IS_EQUAL("is equal comparing to"),

    /** @since 0.13.0 */
    IS_GREATER_THAN_OR_EQUAL("is greater than or equal to"),
    /** @since 0.17.0 */
    IS_NOT_LESS_THAN("is not less than"),

    IS_GREATER_THAN("is greater than"),

    @Deprecated("Use IS_LESS_THAN_OR_EQUAL; will be removed with 1.0.0 at the latest", ReplaceWith("DescriptionComparableAssertion.IS_LESS_THAN_OR_EQUAL"))
    IS_LESS_OR_EQUALS("is less or equals"),
    @Deprecated("Use IS_GREATER_THAN_OR_EQUAL; will be removed with 1.0.0 at the latest", ReplaceWith("DescriptionComparableAssertion.IS_GREATER_THAN_OR_EQUAL"))
    IS_GREATER_OR_EQUALS("is greater or equals"),
}
