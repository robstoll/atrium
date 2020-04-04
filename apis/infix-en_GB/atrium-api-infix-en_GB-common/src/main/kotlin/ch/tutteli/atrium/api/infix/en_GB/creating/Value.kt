@file:Suppress("DEPRECATION" /* TODO remove suppress with 1.0.0 */)
package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries

/**
 * Represents a [Group] with a single value.
 *
 * Use the function `value(t)` to create this representation.
 */
data class Value<T> internal constructor(val expected: T) : GroupWithNullableEntries<T>,
    GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
}
