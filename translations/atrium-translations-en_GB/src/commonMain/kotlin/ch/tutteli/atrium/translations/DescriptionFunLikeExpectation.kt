// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
@Deprecated(
    "Switch to DescriptionFunLikeProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFunLikeProof")
)
enum class DescriptionFunLikeExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFunLikeProof.NO_EXCEPTION_OCCURRED")
    )
    /** @since 0.18.0 */
    NO_EXCEPTION_OCCURRED("❗❗ no exception occurred"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFunLikeProof.THROWN_EXCEPTION_WHEN_CALLED")
    )
    /** @since 0.18.0 */
    THROWN_EXCEPTION_WHEN_CALLED("thrown exception when called"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFunLikeProof.THREW")
    )
    /** @since 0.18.0 */
    THREW("❗❗ threw %s")
}
