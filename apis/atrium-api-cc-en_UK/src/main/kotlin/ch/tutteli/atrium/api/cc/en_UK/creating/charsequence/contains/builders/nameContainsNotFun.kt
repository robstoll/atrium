@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)
package ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.NameContainsNotFunKt.nameContainsNotValuesFun"))
internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<AssertionPlant<CharSequence>, Any, Array<out Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::containsNot
    return f.name
}
