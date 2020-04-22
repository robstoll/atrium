// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.Values
import ch.tutteli.atrium.api.cc.infix.en_GB.containsNot
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction2

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction2<Assert<CharSequence>, Values<Any>, Assert<CharSequence>> = Assert<CharSequence>::containsNot
    return "${f.name} ${Values::class.simpleName}"
}
