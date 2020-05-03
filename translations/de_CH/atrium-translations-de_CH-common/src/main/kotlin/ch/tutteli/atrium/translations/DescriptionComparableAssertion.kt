package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN("ist weniger als"),

    @Deprecated("Use IS_LESS_THAN_OR_EQUALS instead; Will be removed in 1.0.0", ReplaceWith("DescriptionComparableAssertion.IS_LESS_THAN_OR_EQUALS"))
    IS_LESS_OR_EQUALS("ist weniger oder gleich"),

    /**
     * @since 0.12.0
     */
    IS_LESS_THAN_OR_EQUALS("ist weniger als oder gleich wie"),

    IS_GREATER_THAN("ist grösser als"),

    @Deprecated("Use IS_GREATER_THAN_OR_EQUALS instead; Will be removed in 1.0.0", ReplaceWith("DescriptionComparableAssertion.IS_GREATER_THAN_OR_EQUALS"))
    IS_GREATER_OR_EQUALS("ist grösser oder gleich"),

    /**
     * @since 0.12.0
     */
    IS_GREATER_THAN_OR_EQUALS("ist grösser als oder gleich wie"),
}
