package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and [BigDecimal].
 */
@Deprecated(
    "use the description from package translations, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion")
)
enum class DescriptionFloatingPointAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE_WITH_ERROR_TOLERANCE(ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.TO_BE_WITH_ERROR_TOLERANCE.value),
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER(ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.FAILURE_DUE_TO_FLOATING_POINT_NUMBER.value),
    TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED(ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED.value),
}
