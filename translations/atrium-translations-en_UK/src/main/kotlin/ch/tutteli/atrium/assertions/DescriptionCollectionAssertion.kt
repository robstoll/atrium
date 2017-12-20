package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionCollectionAssertion(override val value: String) : StringBasedTranslatable {
    EMPTY("empty"),
    HAS_SIZE("has size"),
}
