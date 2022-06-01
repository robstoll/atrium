//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionThrowableAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "use DescriptionAnyAssertion.IS_A; will be removed with 1.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_A")
    )
    IS_A("is a"),

    @Deprecated(
        "Will be removed with 1.0.0 at the latest (maybe earlier)",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionFunLikeAssertion.NO_EXCEPTION_OCCURRED")
    )
    NO_EXCEPTION_OCCURRED("no exception occurred"),

    @Deprecated(
        "Use DescriptionThrowableExpectation.HAS_NO_CAUSE; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionThrowableExpectation.HAS_NO_CAUSE")
    )
    NOT_CAUSED("❗❗ not caused by another exception"),

    @Deprecated(
        "Use DescriptionThrowableExpectation.OCCURRED_EXCEPTION_PROPERTIES; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionThrowableExpectation.OCCURRED_EXCEPTION_PROPERTIES")
    )
    OCCURRED_EXCEPTION_PROPERTIES("Properties of the unexpected %s"),

    @Deprecated(
        "Use DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE")
    )
    OCCURRED_EXCEPTION_MESSAGE("message"),

    @Deprecated(
        "Use DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE")
    )
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),

    @Deprecated(
        "Use DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE")
    )
    OCCURRED_EXCEPTION_CAUSE("cause"),

    @Deprecated(
        "Use DescriptionThrowableExpectation.OCCURRED_EXCEPTION_SUPPRESSED; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionThrowableExpectation.OCCURRED_EXCEPTION_SUPPRESSED")
    )
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWN_1("is"),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWN_2("not thrown at all"),
}
