package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.enthaeltNicht
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<Assert<CharSequence>, Any, Array<out Any>, Assert<CharSequence>> = Assert<CharSequence>::enthaeltNicht
    return f.name
}
