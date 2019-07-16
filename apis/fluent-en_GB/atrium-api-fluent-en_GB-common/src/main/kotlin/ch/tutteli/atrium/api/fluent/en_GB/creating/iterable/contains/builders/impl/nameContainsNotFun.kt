package ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> = Expect<Iterable<Double>>::containsNot
    return f.name
}
