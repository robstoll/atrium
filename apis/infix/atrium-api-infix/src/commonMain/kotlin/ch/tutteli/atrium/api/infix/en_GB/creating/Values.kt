package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.VarArgHelper
//TODO 1.1.0 check if Note is still valid, I think we don't make it invariant, so remove the note again
/**
 * Represents a [Group] of multiple values.
 *
 * Use the function `values(t, ...)` to create this representation.
 *
 * Note, [Values] will be made invariant once Kotlin 1.4 is out and Atrium depends on it (most likely with 1.0.0)
 */
class Values<out T> internal constructor(
    override val expected: T,
    override val otherExpected: Array<out T>
) : Group<T>, VarArgHelper<T> {
    override fun toList(): List<T> = super.toList()
}
