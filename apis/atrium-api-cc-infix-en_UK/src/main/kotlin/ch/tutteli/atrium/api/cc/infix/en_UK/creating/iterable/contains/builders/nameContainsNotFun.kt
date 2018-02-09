package ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.Values
import ch.tutteli.atrium.api.cc.infix.en_UK.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction2

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction2<AssertionPlant<Iterable<Double>>, Values<Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::containsNot
    return "${f.name} ${Values::class.simpleName}"
}
