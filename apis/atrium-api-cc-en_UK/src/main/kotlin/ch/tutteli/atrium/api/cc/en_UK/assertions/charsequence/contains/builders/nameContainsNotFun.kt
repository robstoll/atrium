package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<AssertionPlant<CharSequence>, Any, Array<out Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::containsNot
    return f.name
}
