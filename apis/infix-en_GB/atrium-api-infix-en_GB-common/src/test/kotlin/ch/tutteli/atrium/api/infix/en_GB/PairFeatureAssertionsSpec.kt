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
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<out Pair<String, Int>> = notImplemented()
        var a2: Expect<Pair<String?, Int>> = notImplemented()
        var a3: Expect<Pair<String?, Int?>> = notImplemented()

        a1.first
        a2.first
        a3.first
        a1 = a1 first { }
        a2 = a2 first { }
        a3 = a3 first { }

        a1.second
        a2.second
        a3.second
        a1 = a1 second { }
        a2 = a2 second { }
        a3 = a3 second { }
    }

    companion object {
        fun first(plant: Expect<Pair<String, Int>>, expectationCreator: Expect<String>.() -> Unit) =
            plant first { expectationCreator() }

        fun second(plant: Expect<Pair<String, Int>>, expectationCreator: Expect<Int>.() -> Unit) =
            plant second { expectationCreator() }
    }
}
