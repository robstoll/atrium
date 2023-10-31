package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.VarArgHelper
/**
 * Represents a [Group] of multiple values.
 *
 * Use the function `values(t, ...)` to create this representation.
 */
class Values<out T> internal constructor(
    override val expected: T,
    override val otherExpected: Array<out T>
) : Group<T>, VarArgHelper<T> {
    override fun toList(): List<T> = super.toList()
}
