package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.Group

/**
 * Represents a [Group] with a single value.
 *
 * Use the function `value(t)` to create this representation.
 */
//TODO 2.0.0 remove data?
data class Value<out T> (val expected: T) : Group<T> {
    override fun toList() = listOf(expected)
}
