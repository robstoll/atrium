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
    IS_A("ist eine"),

    @Deprecated(
        "Will be removed with 1.0.0 at the latest (maybe earlier)",
        ReplaceWith("ch.tutteli.atrium.translations.DescriptionFunLikeAssertion.NO_EXCEPTION_OCCURRED")
    )
    NO_EXCEPTION_OCCURRED("keine Exception wurde geworfen"),
    NOT_CAUSED("❗❗ nicht durch eine andere Exception verursacht"),

    //TODO rename to CAUSE with 1.0.0
    OCCURRED_EXCEPTION_CAUSE("cause"),
    OCCURRED_EXCEPTION_PROPERTIES("Eigenschaften der unerwarteten %s"),
    OCCURRED_EXCEPTION_MESSAGE("message"),
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWN_1("wird"),

    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    IS_NOT_THROWN_2("nicht geworfen"),
}
