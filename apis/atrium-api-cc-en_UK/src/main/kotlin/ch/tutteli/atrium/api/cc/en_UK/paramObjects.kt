package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.domain.builders.utils.Group

/**
 * Represents a [Group] with a single value.
 */
data class Value<T>(private val expected: T): Group<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [Group] of multiple values.
 */
class Values<T>(private val expected: T,  vararg val otherExpected: T): Group<T>{
    override fun toList() = listOf(expected, *otherExpected)
}
