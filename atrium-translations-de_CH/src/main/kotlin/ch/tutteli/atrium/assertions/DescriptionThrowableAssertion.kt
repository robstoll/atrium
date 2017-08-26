package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableAssertion(override val value: String) : ISimpleTranslatable {
    IS_A("ist ein"),
    NO_EXCEPTION_OCCURRED("keine Exception wurde geworfen"),
}
