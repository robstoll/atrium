package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableProof(override val string: String) : Description {

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translationsDescriptionThrowableExpectation)  */
    CAUSE("cause"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translationsDescriptionThrowableExpectation)  */
    HAS_NO_CAUSE("❗❗ not caused by another exception"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translationsDescriptionThrowableExpectation)  */
    OCCURRED_EXCEPTION_CAUSE("cause"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translationsDescriptionThrowableExpectation)  */
    OCCURRED_EXCEPTION_PROPERTIES("Properties of the unexpected"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translationsDescriptionThrowableExpectation)  */
    OCCURRED_EXCEPTION_MESSAGE("message"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translationsDescriptionThrowableExpectation)  */
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),

    /** @since 1.3.0 (but was since 0.18.0  in atrium-translationsDescriptionThrowableExpectation) */
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),
}
