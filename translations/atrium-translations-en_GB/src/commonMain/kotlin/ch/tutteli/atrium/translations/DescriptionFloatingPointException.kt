// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and maybe other platform specific floating point types (such as `BigDecimal` in JVM).
 */
@Deprecated(
    "Switch to DescriptionFloatingPointProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFloatingPointProof")
)
enum class DescriptionFloatingPointException(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFloatingPointProof.TO_EQUAL_WITH_ERROR_TOLERANCE")
    )
    /** @since 0.18.0 */
    TO_EQUAL_WITH_ERROR_TOLERANCE("to equal (error ± %s)"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFloatingPointProof.FAILURE_DUE_TO_FLOATING_POINT_NUMBER")
    )
    /** @since 0.18.0 */
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("failure might be due to using %s, see exact check on the next line"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFloatingPointProof.TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED")
    )
    /** @since 0.18.0 */
    TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED("exact check was |%s - %s| = %s ≤ %s")
}
