// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Comparable].
 */
@Deprecated(
    "Switch to DescriptionComparableProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof")
)
enum class DescriptionComparableExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_LESS_THAN")
    )
    /** @since 0.18.0 */
    TO_BE_LESS_THAN("to be less than"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_LESS_THAN_OR_EQUAL_TO")
    )
    /** @since 0.18.0 */
    TO_BE_LESS_THAN_OR_EQUAL_TO("to be less than or equal to"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.NOT_TO_BE_GREATER_THAN")
    )
    /** @since 0.18.0 */
    NOT_TO_BE_GREATER_THAN("not to be greater than"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_EQUAL_COMPARING_TO")
    )
    /** @since 0.18.0 */
    TO_BE_EQUAL_COMPARING_TO("to be equal comparing to"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_GREATER_THAN_OR_EQUAL_TO")
    )
    /** @since 0.18.0 */
    TO_BE_GREATER_THAN_OR_EQUAL_TO("to be greater than or equal to"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.NOT_TO_BE_LESS_THAN")
    )
    /** @since 0.18.0 */
    NOT_TO_BE_LESS_THAN("not to be less than"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_GREATER_THAN")
    )
    /** @since 0.18.0 */
    TO_BE_GREATER_THAN("to be greater than"),
}
