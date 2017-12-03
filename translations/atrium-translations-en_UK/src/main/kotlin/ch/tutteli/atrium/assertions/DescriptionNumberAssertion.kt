package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Number].
 */
enum class DescriptionNumberAssertion(override val value: String) : ISimpleTranslatable {
    IS_LESS_THAN("is less than"),
    IS_LESS_OR_EQUALS("is less or equals"),
    IS_GREATER_THAN("is greater than"),
    IS_GREATER_OR_EQUALS("is greater or equals"),
}
