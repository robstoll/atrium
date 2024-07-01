package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Function] and co.
 */
enum class DescriptionFunLikeProof(override val string: String) : Description {
    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionFunLikeExpectation) */
    NO_EXCEPTION_OCCURRED("❗❗ no exception occurred"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionFunLikeExpectation) */
    THROWN_EXCEPTION_WHEN_CALLED("thrown exception when called"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionFunLikeExpectation) */
    THREW("❗❗ threw %s")
}
