package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionFloatingPointAssertions(override val value: String) : StringBasedTranslatable {
    TO_BE_WITH_ERROR_TOLERANCE("to be (error ± %s)"),
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("failure might be due to using %s, see exact check on the next line"),
    TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED("exact check is |%s - %s| = %s ≤ %s"),
}
