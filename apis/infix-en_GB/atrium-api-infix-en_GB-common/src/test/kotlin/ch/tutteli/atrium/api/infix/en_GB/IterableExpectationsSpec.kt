package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.reflect.KFunction2

class IterableExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableExpectationsSpec(
    getHasNextPair(),
    getHasNotNextPair(),
    minFeaturePair(),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    maxFeaturePair(),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max),
    getToContainNoDuplicatesPair()
) {
    companion object {
        private val has: KFunction2<Expect<Iterable<Int>>, next, Expect<Iterable<Int>>> = Expect<Iterable<Int>>::has
        private fun getHasNextPair() = "${has.name} ${next::class.simpleName}" to Companion::hasNext
        private fun hasNext(expect: Expect<Iterable<Int>>) = expect has next

        private val hasNot: KFunction2<Expect<Iterable<Int>>, next, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::hasNot

        private fun getHasNotNextPair() = "${hasNot.name} ${next::class.simpleName}" to Companion::hasNotNext
        private fun hasNotNext(expect: Expect<Iterable<Int>>) = expect hasNot next

        private fun minFeaturePair() = feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::min).name to ::minFeature
        private fun minFeature(expect: Expect<Iterable<Int>>) = expect min o

        private fun maxFeaturePair() = feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::min).name to ::maxFeature
        private fun maxFeature(expect: Expect<Iterable<Int>>) = expect max o

        private val notToContainDuplicates: KFunction2<Expect<Iterable<Int>>, duplicates, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::notToContain

        private fun getToContainNoDuplicatesPair() =
            "${notToContainDuplicates.name} ${duplicates::class.simpleName}" to Companion::toContainNoDuplicates

        private fun toContainNoDuplicates(expect: Expect<Iterable<Int>>) = expect notToContain duplicates

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1 has next
        a1 = a1 hasNot next
        a1 = a1 toContain noDuplicates

        a1b = a1b has next
        a1b = a1b hasNot next
        a1b = a1b toContain noDuplicates

        star = star has next
        star = star hasNot next
        star = star toContain noDuplicates

        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>
        a1 min o toEqual 2
        a1 max o toEqual 3

        a1 = a1 min { }
        a1 = a1 max { }
    }
}
