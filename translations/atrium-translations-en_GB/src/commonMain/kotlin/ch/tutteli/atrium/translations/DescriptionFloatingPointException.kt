// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and maybe other platform specific floating point types (such as `BigDecimal` in JVM).
 */
enum class DescriptionFloatingPointException(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    TO_EQUAL_WITH_ERROR_TOLERANCE("to equal (error ± %s)"),

    /** @since 0.18.0 */
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("failure might be due to using %s, see exact check on the next line"),

    /** @since 0.18.0 */
    TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED("exact check was |%s - %s| = %s ≤ %s")
}
