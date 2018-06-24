package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionCollectionAssertion(override val value: String) : StringBasedTranslatable {
    EMPTY("leer"),
}
