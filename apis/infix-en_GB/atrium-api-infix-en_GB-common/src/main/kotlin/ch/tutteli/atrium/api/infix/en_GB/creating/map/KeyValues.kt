package ch.tutteli.atrium.api.infix.en_GB.creating.map

import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `KeyWithValueCreator<K, V>, vararg KeyWithValueCreator<K, V>`.
 *
 * Use the function `keyValues(keyValue(x){ ... }, ...)` to create this representation.
 */
class KeyValues<out K, V: Any> internal constructor(
    override val expected: KeyWithValueCreator<K, V>,
    override val otherExpected: Array<out KeyWithValueCreator<K, V>>
) : VarArgHelper<KeyWithValueCreator<K, V>>
