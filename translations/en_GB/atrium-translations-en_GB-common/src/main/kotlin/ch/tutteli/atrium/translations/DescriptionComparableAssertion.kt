package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN("is less than"),

    @Deprecated("Use IS_LESS_THAN_OR_EQUALS instead; Will be removed in 1.0.0", ReplaceWith("DescriptionComparableAssertion.IS_LESS_THAN_OR_EQUALS"))
    IS_LESS_OR_EQUALS("is less or equals"),

    /**
     * @since 0.12.0
     */
    IS_LESS_THAN_OR_EQUALS("is less than or equals to"),

    IS_GREATER_THAN("is greater than"),

    @Deprecated("Use IS_GREATER_THAN_OR_EQUALS instead; Will be removed in 1.0.0", ReplaceWith("DescriptionComparableAssertion.IS_GREATER_THAN_OR_EQUALS"))
    IS_GREATER_OR_EQUALS("is greater or equals"),

    /**
     * @since 0.12.0
     */
    IS_GREATER_THAN_OR_EQUALS("is greater than or equals to"),
}
