package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class DefaultTranslationsOf(val expected: ITranslatable, val otherExpected: Array<out ITranslatable>) {
    constructor(expected: ITranslatable) : this(expected, emptyArray())
}

class Entries<T : Any>(val assertionCreator: IAssertionPlant<T>.() -> Unit, val otherAssertionCreators: Array<out IAssertionPlant<T>.() -> Unit>) {
    constructor(expected: IAssertionPlant<T>.() -> Unit) : this(expected, emptyArray())
}

class Objects<out T>(val expected: T, val otherExpected: Array<out T>) {
    constructor(expected: T) : this(expected, unsafeEmptyArray())
    constructor(values: Values<T>) : this(values.expected, values.otherExpected)
}

class RegexPatterns(val pattern: String, val otherPatterns: Array<out String>) {
    constructor(expected: String) : this(expected, emptyArray())
}

class Values<out T>(val expected: T, val otherExpected: Array<out T>) {
    constructor(expected: T) : this(expected, unsafeEmptyArray())
}

@Suppress("UNCHECKED_CAST")
private fun <T> unsafeEmptyArray() = arrayOfNulls<Any>(0) as Array<T>
