package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Contains the [IBasicAssertion.description]s which are so basic that one does not want to use a different wording in
 * a two different assertion functions.
 */
enum class DescriptionBasic(override val value: String): ISimpleTranslatable {
    IS("is"),
    IS_NOT("is not")
}
