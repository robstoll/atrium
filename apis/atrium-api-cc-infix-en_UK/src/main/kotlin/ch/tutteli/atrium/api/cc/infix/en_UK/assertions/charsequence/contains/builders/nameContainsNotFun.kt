package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.Values
import ch.tutteli.atrium.api.cc.infix.en_UK.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction2

@Deprecated("Use function from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.nameContainsNotValuesFun"))
fun nameContainsNotValuesFun(): String {
    val f: KFunction2<AssertionPlant<CharSequence>, Values<*>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::containsNot
    return "${f.name} ${Values::class.simpleName}"
}
