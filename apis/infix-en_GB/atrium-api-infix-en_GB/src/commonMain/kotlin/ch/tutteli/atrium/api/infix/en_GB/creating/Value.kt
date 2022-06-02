package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.Group

/**
 * Represents a [Group] with a single value.
 *
 * Use the function `value(t)` to create this representation.
 */
data class Value<T> internal constructor(val expected: T) : Group<T> {
    override fun toList() = listOf(expected)
}
