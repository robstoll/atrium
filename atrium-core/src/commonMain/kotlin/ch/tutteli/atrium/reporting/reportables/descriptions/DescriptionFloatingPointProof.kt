package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to floating point numbers.
 */
enum class DescriptionFloatingPointProof(override val string: String) : Description {
    /** @since 1.3.0 (was in DescriptionFloatingPointExpectation since 0.18.0) */
    TO_EQUAL_WITH_ERROR_TOLERANCE("to equal (error ± %s)"),

    /** @since 1.3.0 (was in DescriptionFloatingPointExpectation since 0.18.0) */
    FAILURE_DUE_TO_FLOATING_POINT_NUMBER("failure might be due to using %s, see exact check on the next line"),

    /** @since 1.3.0 (was in DescriptionFloatingPointExpectation since 0.18.0) */
    TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED("exact check was |%s - %s| = %s ≤ %s")
}
