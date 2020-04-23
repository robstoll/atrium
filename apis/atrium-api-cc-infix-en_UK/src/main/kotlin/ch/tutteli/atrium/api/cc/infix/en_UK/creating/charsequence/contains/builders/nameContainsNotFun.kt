// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.Values
import ch.tutteli.atrium.api.cc.infix.en_UK.containsNot
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction2

@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.nameContainsNotValuesFun"))
internal fun nameContainsNotValuesFun(): String {
    val f: KFunction2<Assert<CharSequence>, Values<Any>, Assert<CharSequence>> = Assert<CharSequence>::containsNot
    return "${f.name} ${Values::class.simpleName}"
}
