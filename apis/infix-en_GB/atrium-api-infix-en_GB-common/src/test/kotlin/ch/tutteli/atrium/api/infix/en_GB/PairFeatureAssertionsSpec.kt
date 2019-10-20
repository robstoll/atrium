package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1
import ch.tutteli.atrium.api.infix.en_GB.*

class PairFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.PairFeatureAssertionsSpec(
    property<Pair<String, Int>, String>(Expect<Pair<String, Int>>::first),
    fun1<Pair<String, Int>, Expect<String>.() -> Unit>(Expect<Pair<String, Int>>::first),
    property<Pair<String, Int>, Int>(Expect<Pair<String, Int>>::second),
    fun1<Pair<String, Int>, Expect<Int>.() -> Unit>(Expect<Pair<String, Int>>::second),
    property<Pair<String?, Int?>, String?>(Expect<Pair<String?, Int?>>::first),
    fun1<Pair<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Pair<String?, Int?>>::first),
    property<Pair<String?, Int?>, Int?>(Expect<Pair<String?, Int?>>::second),
    fun1<Pair<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Pair<String?, Int?>>::second)
) {
    companion object {
        fun first(plant: Expect<Pair<String, Int>>, expectationCreator: Expect<String>.() -> Unit) =
            plant first { expectationCreator() }

        fun second(plant: Expect<Pair<String, Int>>, expectationCreator: Expect<Int>.() -> Unit) =
            plant second { expectationCreator() }
    }
}
