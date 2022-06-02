//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and maybe other platform specific floating point types (such as `BigDecimal` in JVM).
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionFloatingPointAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFloatingPointException.TO_EQUAL_WITH_ERROR_TOLERANCE")
    )
    TO_BE_WITH_ERROR_TOLERANCE("ist (mit Fehler ± %s)"),
    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFloatingPointException.FAILURE_DUE_TO_FLOATING_POINT_NUMBER")
    )
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("Fehler ist womöglich der Nutzung von %s geschuldet, siehe exakter Vergleich auf der nächsten Linie"),
    @Deprecated(
        "Use DescriptionFloatingPointException instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionFloatingPointException.TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED")
    )
    TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED("exakter Vergleich ist |%s - %s| = %s ≤ %s"),
}
