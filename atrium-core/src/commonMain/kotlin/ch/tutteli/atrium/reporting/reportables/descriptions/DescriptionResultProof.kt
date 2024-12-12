package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Result].
 */
enum class DescriptionResultProof(override val string: String) : Description {
    /** @since 1.3.0 (was in DescriptionResultExpectation since 0.18.0) */
    IS_NOT_SUCCESS("❗❗ is not a Success"),

    /** @since 1.3.0 (was in DescriptionResultExpectation since 0.18.0) */
    VALUE("value"),

    /** @since 1.3.0 (was in DescriptionResultExpectation since 0.18.0) */
    IS_NOT_FAILURE("❗❗ is not failure"),

    /** @since 1.3.0 (was in DescriptionResultExpectation since 0.18.0) */
    EXCEPTION("exception")
}
