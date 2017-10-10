package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionCollectionAssertion(override val value: String) : ISimpleTranslatable {
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    EMPTY("empty"),
    HAS_SIZE("has size"),
}
