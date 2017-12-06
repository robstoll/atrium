package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.Values
import ch.tutteli.atrium.api.cc.infix.en_UK.containsNot
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction2

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction2<IAssertionPlant<Iterable<Double>>, Values<Double>, IAssertionPlant<Iterable<Double>>> = IAssertionPlant<Iterable<Double>>::containsNot
    return "${f.name} ${Values::class.simpleName}"
}
