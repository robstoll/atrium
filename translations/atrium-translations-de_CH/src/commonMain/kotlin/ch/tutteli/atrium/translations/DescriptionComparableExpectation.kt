package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionComparableExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_LESS_THAN("ist weniger als"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_LESS_THAN_OR_EQUAL_TO("ist weniger als oder gleich wie"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    NOT_TO_BE_GREATER_THAN("ist nicht grösser als"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_EQUAL_COMPARING_TO("ist gleich wie"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_GREATER_THAN_OR_EQUAL_TO("ist grösser als oder gleich wie"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    NOT_TO_BE_LESS_THAN("ist nicht weniger als"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    TO_BE_GREATER_THAN("ist grösser als"),
}
