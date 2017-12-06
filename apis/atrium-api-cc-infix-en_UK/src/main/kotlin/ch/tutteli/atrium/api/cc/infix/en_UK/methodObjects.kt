package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class DefaultTranslationsOf(val expected: ITranslatable, vararg val otherExpected: ITranslatable)
class Entries<T : Any>(val assertionCreator: IAssertionPlant<T>.() -> Unit, vararg val otherAssertionCreators: IAssertionPlant<T>.() -> Unit)
class RegexPatterns(val pattern: String, vararg val otherPatterns: String)
class Objects<out E>(val expected: E, vararg val otherExpected: E) {
    constructor(values: Values<E>) : this(values.expected, *values.otherExpected)
}

class Values<out E>(val expected: E, vararg val otherExpected: E)
