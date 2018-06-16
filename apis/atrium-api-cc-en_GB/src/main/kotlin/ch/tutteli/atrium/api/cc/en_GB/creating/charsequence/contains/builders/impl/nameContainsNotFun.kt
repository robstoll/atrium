package ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<Assert<CharSequence>, Any, Array<out Any>, Assert<CharSequence>> = Assert<CharSequence>::containsNot
    return f.name
}
