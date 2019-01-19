package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableAssertion(override val value: String) : StringBasedTranslatable {
    IS_A("is a"),
    NO_EXCEPTION_OCCURRED("no exception occurred"),
    OCCURRED_EXCEPTION_CAUSE("cause"),
    OCCURRED_EXCEPTION_PROPERTIES("Properties of the unexpected %s"),
    OCCURRED_EXCEPTION_MESSAGE("message"),
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),
    IS_NOT_THROWN_1("is"),
    IS_NOT_THROWN_2("not thrown at all"),
}
