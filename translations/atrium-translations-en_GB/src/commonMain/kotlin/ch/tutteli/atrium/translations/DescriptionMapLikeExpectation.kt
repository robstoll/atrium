// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated(
    "Switch to DescriptionMapLikeProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof")
)
enum class DescriptionMapLikeExpectation(override val value: String) : StringBasedTranslatable {
     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.TO_CONTAIN")
    )
    /** @since 0.18.0 */
    TO_CONTAIN("to contain"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.TO_CONTAIN_KEY")
    )
    /** @since 0.18.0 */
    TO_CONTAIN_KEY("to contain key"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.NOT_TO_CONTAIN_KEY")
    )
    /** @since 0.18.0 */
    NOT_TO_CONTAIN_KEY("not to contain key"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.ENTRY_WITH_KEY")
    )
    /** @since 0.18.0 */
    ENTRY_WITH_KEY("entry %s"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.IN_ANY_ORDER")
    )
    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in any order"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.IN_ANY_ORDER_ONLY")
    )
    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s only, in any order"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.IN_ORDER")
    )
    /** @since 0.18.0 */
    IN_ORDER("%, in order"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.IN_ORDER_ONLY")
    )
    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s only, in order"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.KEY_DOES_NOT_EXIST")
    )
    /** @since 0.18.0 */
    KEY_DOES_NOT_EXIST("❗❗ key does not exist"),

     @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof.WARNING_ADDITIONAL_ENTRIES")
    )
    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ENTRIES("additional entries detected"),
}
