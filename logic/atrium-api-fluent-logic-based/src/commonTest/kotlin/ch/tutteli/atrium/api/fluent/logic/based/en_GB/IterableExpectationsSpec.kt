package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

object IterableExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableExpectationsSpec(
    fun0(Expect<Iterable<Int>>::toHaveElements),
    fun0(Expect<Iterable<Int>>::notToHaveElements),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::min),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::max),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max),
    fun0(Expect<Iterable<Int>>::toHaveElementsAndNoDuplicates),
    property<Iterable<Int>, Int>(Expect<Iterable<Int>>::last),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::last),
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.toHaveElements()
        a1 = a1.notToHaveElements()
        a1 = a1.toHaveElementsAndNoDuplicates()

        a1b = a1b.toHaveElements()
        a1b = a1b.notToHaveElements()
        a1b = a1b.toHaveElementsAndNoDuplicates()

        star = star.toHaveElements()
        star = star.notToHaveElements()
        star = star.toHaveElementsAndNoDuplicates()

        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>
        a1.min()
        a1.max()

        a1 = a1.min { }
        a1 = a1.max { }

        a1.last
        a1.last { }
    }
}
