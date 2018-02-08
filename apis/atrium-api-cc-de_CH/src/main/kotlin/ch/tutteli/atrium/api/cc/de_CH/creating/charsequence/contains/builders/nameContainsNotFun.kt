package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.enthaeltNicht
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<AssertionPlant<CharSequence>, Any, Array<out Any>, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::enthaeltNicht
    return f.name
}
