package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

class DefaultTranslationsOf(val expected: Translatable, vararg val otherExpected: Translatable)

class Entries<T : Any>(val assertionCreator: AssertionPlant<T>.() -> Unit, vararg val otherAssertionCreators: AssertionPlant<T>.() -> Unit)

class Objects<out T>(val expected: T, vararg val otherExpected: T) {
    constructor(values: Values<T>) : this(values.expected, *values.otherExpected)
}

class RegexPatterns(val pattern: String, vararg val otherPatterns: String)
class Values<out T>(val expected: T, vararg val otherExpected: T)
