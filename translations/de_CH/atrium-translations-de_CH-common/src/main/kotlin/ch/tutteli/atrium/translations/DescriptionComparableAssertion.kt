package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    IS_LESS_THAN("ist weniger als"),

    /** @since 0.13.0 */
    IS_LESS_THAN_OR_EQUAL("ist weniger als oder gleich wie"),
    /** @since 0.17.0 */
    IS_NOT_GREATER_THAN("ist nicht grösser als"),

    IS_EQUAL("ist gleich wie"),

    /** @since 0.13.0 */
    IS_GREATER_THAN_OR_EQUAL("ist grösser als oder gleich wie"),
    /** @since 0.17.0 */
    IS_NOT_LESS_THAN("ist nicht weniger als"),

    IS_GREATER_THAN("ist grösser als"),

    @Deprecated("Use IS_LESS_THAN_OR_EQUAL; will be removed with 1.0.0 at the latest", ReplaceWith("DescriptionComparableAssertion.IS_LESS_THAN_OR_EQUAL"))
    IS_LESS_OR_EQUALS("ist weniger oder gleich"),
    @Deprecated("Use IS_GREATER_THAN_OR_EQUAL; will be removed with 1.0.0 at the latest", ReplaceWith("DescriptionComparableAssertion.IS_GREATER_THAN_OR_EQUAL"))
    IS_GREATER_OR_EQUALS("ist grösser oder gleich"),
}
