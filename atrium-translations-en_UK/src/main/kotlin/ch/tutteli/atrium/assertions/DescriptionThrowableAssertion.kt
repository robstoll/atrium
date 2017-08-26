package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableAssertion(override val value: String) : ISimpleTranslatable {
    IS_A("is a"),
    NO_EXCEPTION_OCCURRED("no exception occurred"),
}
