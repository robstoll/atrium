package ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.nameContainsNotValuesFun"))
internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::containsNot
    return f.name
}
