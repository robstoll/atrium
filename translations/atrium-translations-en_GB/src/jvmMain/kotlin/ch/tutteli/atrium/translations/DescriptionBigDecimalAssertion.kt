// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
@Deprecated(
    "Switch to DescriptionBigDecimalProof, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof")
)
enum class DescriptionBigDecimalAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL")
    )
    FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL("notice, if you used %s then the expectation would have been met."),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof.IS_EQUAL_INCLUDING_SCALE")
    )
    IS_EQUAL_INCLUDING_SCALE("is equal (including scale)"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof.IS_NOT_EQUAL_INCLUDING_SCALE")
    )
    IS_NOT_EQUAL_INCLUDING_SCALE("is not equal (including scale)"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof.IS_NUMERICALLY_EQUAL_TO")
    )
    IS_NUMERICALLY_EQUAL_TO("is numerically equal to"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof.IS_NOT_NUMERICALLY_EQUAL_TO")
    )
    IS_NOT_NUMERICALLY_EQUAL_TO("is not numerically equal to"),
}
