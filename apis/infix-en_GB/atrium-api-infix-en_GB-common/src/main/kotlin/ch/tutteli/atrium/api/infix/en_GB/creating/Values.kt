@file:Suppress("DEPRECATION" /* TODO remove suppress with 1.0.0 */)

package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Represents a [Group] of multiple values.
 *
 * Note, [Values] will be made invariant once Kotlin 1.4 is out and Atrium depends on it (most likely with 1.0.0)
 */
//TODO remove `out` with Kotlin 1.4 (most likely with Atrium 1.0.0)
class Values<out T>(
    override val expected: T,
    override vararg val otherExpected: T
) : GroupWithoutNullableEntries<T>, GroupWithNullableEntries<T>, VarArgHelper<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
