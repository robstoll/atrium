package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableAssertion(override val value: String) : StringBasedTranslatable {
    IS_A("is a"),
    NO_EXCEPTION_OCCURRED("no exception occurred"),
}
