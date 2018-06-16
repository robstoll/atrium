package ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<Assert<Iterable<Double>>, Double, Array<out Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
    return f.name
}
