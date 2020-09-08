@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction3

internal object StaticName {
    private val f: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
        Expect<Iterable<Double>>::containsNot
    val nameContainsNotValuesFun = f.name
}
