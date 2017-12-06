package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class DefaultTranslationsOf(val expected: ITranslatable, vararg val otherExpected: ITranslatable)
class Entries<T : Any>(val assertionCreator: IAssertionPlant<T>.() -> Unit, vararg val otherAssertionCreators: IAssertionPlant<T>.() -> Unit)
class RegexPatterns(val pattern: String, vararg val otherPatterns: String)

class Objects<out T>(val expected: T, val otherExpected: Array<out T>) {
    constructor(expected: T) : this(expected, emptyArray())
    constructor(values: Values<T>) : this(values.expected, values.otherExpected)
}

class Values<out T>(val expected: T, val otherExpected: Array<out T>) {
    constructor(expected: T) : this(expected, emptyArray())
}

@Suppress("UNCHECKED_CAST")
private fun <T> emptyArray() = kotlin.emptyArray<Any>() as Array<T>
