// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

@Deprecated(
    "Switch to DescriptionOptionalProof, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionOptionalProof")
)
enum class DescriptionOptionalAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionOptionalProof.EMPTY")
    )
    EMPTY("empty"),
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionOptionalProof.GET")
    )
    GET("get()"),
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionOptionalProof.IS_NOT_PRESENT")
    )
    IS_NOT_PRESENT("❗❗ is not present")
}
