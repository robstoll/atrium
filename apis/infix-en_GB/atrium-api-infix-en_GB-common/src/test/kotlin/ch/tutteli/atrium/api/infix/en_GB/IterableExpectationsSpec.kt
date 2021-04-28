package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.reflect.KFunction2

class IterableExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableExpectationsSpec(
    getToHaveNextPair(),
    getNotToHaveNextPair(),
    minFeaturePair(),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    maxFeaturePair(),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max),
    getToContainNoDuplicatesPair()
) {
    companion object {
        private val toHave: KFunction2<Expect<Iterable<Int>>, next, Expect<Iterable<Int>>> = Expect<Iterable<Int>>::toHave
        private fun getToHaveNextPair() = "${toHave.name} ${next::class.simpleName}" to Companion::toHaveNext
        private fun toHaveNext(expect: Expect<Iterable<Int>>) = expect toHave next

        private val notToHave: KFunction2<Expect<Iterable<Int>>, next, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::notToHave

        private fun getNotToHaveNextPair() = "${notToHave.name} ${next::class.simpleName}" to Companion::notToHaveNext
        private fun notToHaveNext(expect: Expect<Iterable<Int>>) = expect notToHave next

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

        a1 = a1 toHave next
        a1 = a1 notToHave next
        a1 = a1 notToContain duplicates

        a1b = a1b toHave next
        a1b = a1b notToHave next
        a1b = a1b notToContain duplicates

        star = star toHave next
        star = star notToHave next
        star = star notToContain duplicates

        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>
        a1 min o toEqual 2
        a1 max o toEqual 3

        a1 = a1 min { }
        a1 = a1 max { }
    }
}
