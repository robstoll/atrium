package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.VarArgHelper

/**
 * Represents a [Group] of multiple values.
 *
 * Use the function `values(t, ...)` to create this representation.
 *
 * Note, [Values] will be made invariant once Kotlin 1.4 is out and Atrium depends on it (most likely with 1.0.0)
 */
// TODO remove `out` with Kotlin 1.4 (most likely with Atrium 1.1.0)
// but check infix-api, things like `the values("A", 1)` where the subject is List<Number> should be prevented
// on the other hand, we don't want to need to specify `the values<Number?>(1, 1.2)` in case the subject is List<Number?>
class Values<out T> internal constructor(
    override val expected: T,
    override val otherExpected: Array<out T>
) : Group<T>, VarArgHelper<T> {
    override fun toList(): List<T> = super.toList()
}
