// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
@Deprecated(
    "Switch to DescriptionThrowableProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof")
)
enum class DescriptionThrowableExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.CAUSE")
    )
    /** @since 0.18.0 */
    CAUSE("cause"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.HAS_NO_CAUSE")
    )
    /** @since 0.18.0 */
    HAS_NO_CAUSE("❗❗ not caused by another exception"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.OCCURRED_EXCEPTION_CAUSE")
    )
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_CAUSE("cause"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.OCCURRED_EXCEPTION_PROPERTIES")
    )
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_PROPERTIES("Properties of the unexpected %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.OCCURRED_EXCEPTION_MESSAGE")
    )
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_MESSAGE("message"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.OCCURRED_EXCEPTION_STACKTRACE")
    )
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.OCCURRED_EXCEPTION_SUPPRESSED")
    )
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),
}
