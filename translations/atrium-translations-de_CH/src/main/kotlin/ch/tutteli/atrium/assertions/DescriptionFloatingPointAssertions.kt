package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionFloatingPointAssertions(override val value: String) : StringBasedTranslatable {
    TO_BE_WITH_ERROR_TOLERANCE("ist (mit Fehler ± %s)"),
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("Fehler ist womöglich der Nutzung von %s geschuldet, siehe exakter Vergleich auf der nächsten Linie"),
    TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED("exakter Vergleich ist |%s - %s| = %s ≤ %s"),
}
