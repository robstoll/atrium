package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and maybe other platform specific floating point types (such as `BigDecimal` in JVM).
 */
enum class DescriptionFloatingPointAssertion(override val value: String) : StringBasedTranslatable {
    TO_EQUAL_WITH_ERROR_TOLERANCE("to equal (error ± %s)"),
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("failure might be due to using %s, see exact check on the next line"),
    TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED("exact check is |%s - %s| = %s ≤ %s"),

    @Deprecated("Use TO_EQUAL_WITH_ERROR_TOLERANCE; will be removed with 0.18.0", ReplaceWith("TO_EQUAL_WITH_ERROR_TOLERANCE"))
    TO_BE_WITH_ERROR_TOLERANCE(TO_EQUAL_WITH_ERROR_TOLERANCE.getDefault()),
}
