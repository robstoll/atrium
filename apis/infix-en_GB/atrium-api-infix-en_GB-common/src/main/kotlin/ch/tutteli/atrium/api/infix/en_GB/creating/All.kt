package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `T, vararg T`.
 *
 * Use the function `all(t, ...)` to create this representation.
 */
class All<out T> internal constructor(
    override val expected: T,
    override val otherExpected: Array<out T>
) : VarArgHelper<T>
