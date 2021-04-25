package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

object IterableExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableExpectationsSpec(
    fun0(Expect<Iterable<Int>>::toHaveNext),
    fun0(Expect<Iterable<Int>>::notToHaveNext),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::min),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::max),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max),
    fun0(Expect<Iterable<Int>>::notToContainDuplicates)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.toHaveNext()
        a1 = a1.notToHaveNext()
        a1 = a1.notToContainDuplicates()

        a1b = a1b.toHaveNext()
        a1b = a1b.notToHaveNext()
        a1b = a1b.notToContainDuplicates()

        star = star.toHaveNext()
        star = star.notToHaveNext()
        star = star.notToContainDuplicates()

        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>
        a1.min()
        a1.max()

        a1 = a1.min { }
        a1 = a1.max { }
    }
}
