package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import kotlin.reflect.KFunction2
import kotlin.test.Test

class IterableExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractIterableExpectationsTest(
    getToHaveElementsPair(),
    getNotToHaveElementsPair(),
    minFeaturePair(),
    fun1(Expect<Iterable<Int>>::min),
    maxFeaturePair(),
    fun1(Expect<Iterable<Int>>::max),
    getToHaveElementsAndNoDuplicatesPair(),
    lastFeaturePair(),
    fun1(Expect<Iterable<Int>>::last),
) {
    companion object {
        private val toHave: KFunction2<Expect<Iterable<Int>>, elements, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::toHave

        private fun getToHaveElementsPair() =
            "${toHave.name} ${elements::class.simpleName}" to Companion::toHaveElements

        private fun toHaveElements(expect: Expect<Iterable<Int>>) = expect toHave elements

        private val notToHave: KFunction2<Expect<Iterable<Int>>, elements, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::notToHave

        private fun getNotToHaveElementsPair() =
            "${notToHave.name} ${elements::class.simpleName}" to Companion::notToHaveElements

        private fun notToHaveElements(expect: Expect<Iterable<Int>>) = expect notToHave elements

        private fun minFeaturePair() = feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::min).name to ::minFeature
        private fun minFeature(expect: Expect<Iterable<Int>>) = expect min o

        private fun maxFeaturePair() = feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::max).name to ::maxFeature
        private fun maxFeature(expect: Expect<Iterable<Int>>) = expect max o

        private val toHaveElementsAnd: KFunction2<Expect<Iterable<Int>>, noDuplicates, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::toHaveElementsAnd

        private fun getToHaveElementsAndNoDuplicatesPair() =
            "${toHaveElementsAnd.name} ${noDuplicates::class.simpleName}" to Companion::toHaveElementsAndNoDuplicates

        private fun toHaveElementsAndNoDuplicates(expect: Expect<Iterable<Int>>) = expect toHaveElementsAnd noDuplicates

        private fun lastFeaturePair() =
            feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::last).name to ::lastFeature

        private fun lastFeature(expect: Expect<Iterable<Int>>) = expect last o
    }

    @Test
    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    fun ambiguityTest() {
        var list: Expect<List<Double>> = ch.tutteli.atrium.api.verbs.expect(listOf(1.2, 4.3))
        var listEmpty: Expect<List<Double>> = ch.tutteli.atrium.api.verbs.expect(emptyList())
        var nSet: Expect<Set<Double?>> = ch.tutteli.atrium.api.verbs.expect(setOf(1.2, null))
        var nSetEmpty: Expect<Set<Double?>> = ch.tutteli.atrium.api.verbs.expect(emptySet())
        var star: Expect<Collection<*>> = ch.tutteli.atrium.api.verbs.expect(listOf(1.2, 1.3))
        var starEmpty: Expect<Collection<*>> = ch.tutteli.atrium.api.verbs.expect(emptySet<Any?>())

        list = list toHave elements
        list = list toHaveElementsAnd noDuplicates
        listEmpty = listEmpty notToHave elements

        nSet = nSet toHave elements
        nSet = nSet toHaveElementsAnd noDuplicates
        nSetEmpty = nSetEmpty notToHave elements


        star = star toHave elements
        star = star toHaveElementsAnd noDuplicates
        starEmpty = starEmpty notToHave elements

        // nullable not supported by min/max or rather T : Comparable<T> does not exist for T?
        // (one cannot implement an interface for the nullable type) same for Iterable<*>
        val l1: Expect<Double> = list min o
        val l2: Expect<Double> = list max o

        list = list min { toBeLessThan(1.3) }
        list = list max { toBeGreaterThan(4.1) }

        val l3: Expect<Double> = list last o
        list last { toEqual(4.3) }
    }
}
