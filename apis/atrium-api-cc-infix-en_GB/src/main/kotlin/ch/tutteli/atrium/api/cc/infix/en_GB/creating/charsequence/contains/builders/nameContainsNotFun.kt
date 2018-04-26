package ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_GB.Values
import ch.tutteli.atrium.api.cc.infix.en_GB.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction2

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction2<AssertionPlant<CharSequence>, Values<*>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::containsNot
    return "${f.name} ${Values::class.simpleName}"
}
