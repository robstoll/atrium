package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionAnyAssertion(override val value: String) : ISimpleTranslatable {
    TO_BE("to be"),
    NOT_TO_BE("not to be"),
    IS_SAME("is the same as"),
    IS_NOT_SAME("is not the same as"),
}
