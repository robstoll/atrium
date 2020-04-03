package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `T, vararg T`.
 */
class All<out T>(override val expected: T, override vararg val otherExpected: T) :
    VarArgHelper<T>
