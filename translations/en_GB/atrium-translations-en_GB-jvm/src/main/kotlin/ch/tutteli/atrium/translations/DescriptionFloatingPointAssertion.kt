package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and [BigDecimal].
 */
enum class DescriptionFloatingPointAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE_WITH_ERROR_TOLERANCE("to be (error ± %s)"),
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("failure might be due to using %s, see exact check on the next line"),
    TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED("exact check is |%s - %s| = %s ≤ %s"),
}
