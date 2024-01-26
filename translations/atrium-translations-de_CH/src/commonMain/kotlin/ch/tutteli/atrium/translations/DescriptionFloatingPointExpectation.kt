@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Float], [Double]
 * and maybe other platform specific floating point types (such as `BigDecimal` in JVM).
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class DescriptionFloatingPointExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    TO_EQUAL_WITH_ERROR_TOLERANCE("ist (mit Fehler ± %s)"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("Fehler ist womöglich der Nutzung von %s geschuldet, siehe exakter Vergleich auf der nächsten Linie"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED("exakter Vergleich war |%s - %s| = %s ≤ %s"),
}
