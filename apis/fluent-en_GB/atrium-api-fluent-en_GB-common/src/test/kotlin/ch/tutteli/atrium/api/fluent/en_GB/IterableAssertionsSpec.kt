package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

object IterableAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAssertionsSpec(
    fun0(Expect<Iterable<Int>>::hasNext),
    fun0(Expect<Iterable<Int>>::hasNotNext),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::min),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::max),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.hasNext()
        a1 = a1.hasNotNext()

        a1b = a1b.hasNext()
        a1b = a1b.hasNotNext()

        star = star.hasNext()
        star = star.hasNotNext()

        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>
        a1.min()
        a1.max()

        a1 = a1.min { }
        a1 = a1.max { }
    }
}
