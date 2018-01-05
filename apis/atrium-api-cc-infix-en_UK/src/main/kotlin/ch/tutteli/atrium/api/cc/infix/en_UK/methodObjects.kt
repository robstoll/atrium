package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable


class DefaultTranslationsOf(val expected: Translatable, vararg val otherExpected: Translatable)

class Entries<in T : Any, out A : ((Assert<T>) -> Unit)?>(val assertionCreator: A, vararg val otherAssertionCreators: A)

class Objects<out T>(val expected: T, vararg val otherExpected: T) {
    constructor(values: Values<T>) : this(values.expected, *values.otherExpected)
}

class RegexPatterns(val pattern: String, vararg val otherPatterns: String)
class Values<out T>(val expected: T, vararg val otherExpected: T)
