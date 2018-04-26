package ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::containsNot
    return f.name
}
