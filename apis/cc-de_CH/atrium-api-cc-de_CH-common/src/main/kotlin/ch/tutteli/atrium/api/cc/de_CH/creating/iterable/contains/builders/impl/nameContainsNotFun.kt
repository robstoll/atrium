@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.enthaeltNicht
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<AssertionPlant<Iterable<Double>>, Double, Array<out Double>, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::enthaeltNicht
    return f.name
}
