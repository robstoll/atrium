//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionComparableAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_LESS_THAN")
    )
    IS_LESS_THAN("ist weniger als"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_LESS_THAN_OR_EQUAL_TO")
    )
    /** @since 0.13.0 */
    IS_LESS_THAN_OR_EQUAL("ist weniger als oder gleich wie"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.NOT_TO_BE_GREATER_THAN")
    )
    /** @since 0.17.0 */
    IS_NOT_GREATER_THAN("ist nicht grösser als"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_EQUAL_COMPARING_TO")
    )
    IS_EQUAL("ist gleich wie"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_GREATER_THAN_OR_EQUAL_TO")
    )
    /** @since 0.13.0 */
    IS_GREATER_THAN_OR_EQUAL("ist grösser als oder gleich wie"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.NOT_TO_BE_LESS_THAN")
    )
    /** @since 0.17.0 */
    IS_NOT_LESS_THAN("ist nicht weniger als"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_GREATER_THAN")
    )
    IS_GREATER_THAN("ist grösser als"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_LESS_THAN_OR_EQUAL_TO")
    )
    IS_LESS_OR_EQUALS("ist weniger oder gleich"),

    @Deprecated(
        "Use DescriptionComparableExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionComparableExpectation.TO_BE_GREATER_THAN_OR_EQUAL_TO")
    )
    IS_GREATER_OR_EQUALS("ist grösser oder gleich"),
}
