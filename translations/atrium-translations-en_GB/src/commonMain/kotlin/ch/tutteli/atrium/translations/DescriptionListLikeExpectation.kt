// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated(
    "Switch to DescriptionListLikeProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionListLikeProof")
)
enum class DescriptionListLikeExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionListLikeProof.INDEX_OUT_OF_BOUNDS")
    )
    /** @since 0.18.0 */
    INDEX_OUT_OF_BOUNDS("❗❗ Index out of bounds")
}
