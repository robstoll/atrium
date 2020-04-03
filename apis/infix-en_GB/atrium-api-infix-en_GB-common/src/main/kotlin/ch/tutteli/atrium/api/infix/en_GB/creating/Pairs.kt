package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `Pair<K, V>, vararg Pair<K, V>`.
 *
 * Use the function `pairs(x to y, ...)` to create this representation.
 */
class Pairs<out K, out V>(
    override val expected: Pair<K, V>,
    override val otherExpected: Array<out Pair<K, V>>
) : VarArgHelper<Pair<K, V>>
