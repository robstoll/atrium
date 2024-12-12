// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated(
    "Switch to DescriptionResultProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionResultProof")
)
enum class DescriptionResultExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionResultProof.IS_NOT_SUCCESS")
    )
    /** @since 0.18.0 */
    IS_NOT_SUCCESS("❗❗ is not a Success"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionResultProof.VALUE")
    )
    /** @since 0.18.0 */
    VALUE("value"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionResultProof.IS_NOT_FAILURE")
    )
    /** @since 0.18.0 */
    IS_NOT_FAILURE("❗❗ is not failure"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionResultProof.EXCEPTION")
    )
    /** @since 0.18.0 */
    EXCEPTION("exception")
}
