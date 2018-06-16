package ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.NameContainsNotFunKt.nameContainsNotValuesFun"))
internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<Assert<CharSequence>, Any, Array<out Any>, Assert<CharSequence>> = Assert<CharSequence>::containsNot
    return f.name
}
