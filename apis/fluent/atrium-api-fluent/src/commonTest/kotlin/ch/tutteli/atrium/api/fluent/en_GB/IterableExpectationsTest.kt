package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.property
import kotlin.test.Test

class IterableExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractIterableExpectationsTest(
    fun0(Expect<Iterable<Int>>::toHaveElements),
    fun0(Expect<Iterable<Int>>::notToHaveElements),
    feature0(Expect<Iterable<Int>>::min),
    fun1(Expect<Iterable<Int>>::min),
    feature0(Expect<Iterable<Int>>::max),
    fun1(Expect<Iterable<Int>>::max),
    fun0(Expect<Iterable<Int>>::toHaveElementsAndNoDuplicates),
    property(Expect<Iterable<Int>>::last),
    fun1(Expect<Iterable<Int>>::last),
) {

    @Test
    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    fun ambiguityTest() {
        var list: Expect<List<Double>> = expect(listOf(1.2, 4.3))
        var listEmpty: Expect<List<Double>> = expect(emptyList())
        var nSet: Expect<Set<Double?>> = expect(setOf(1.2, null))
        var nSetEmpty: Expect<Set<Double?>> = expect(emptySet())
        var star: Expect<Collection<*>> = expect(listOf(1.2, 1.3))
        var starEmpty: Expect<Collection<*>> = expect(emptySet<Any?>())

        list = list.toHaveElements()
        list = list.toHaveElementsAndNoDuplicates()
        listEmpty = listEmpty.notToHaveElements()

        nSet = nSet.toHaveElements()
        nSet = nSet.toHaveElementsAndNoDuplicates()
        nSetEmpty = nSetEmpty.notToHaveElements()


        star = star.toHaveElements()
        star = star.toHaveElementsAndNoDuplicates()
        starEmpty = starEmpty.notToHaveElements()

        // nullable not supported by min/max or rather T : Comparable<T> does not exist for T?
        // (one cannot implement an interface for the nullable type) same for Iterable<*>
        val l1: Expect<Double> = list.min()
        val l2: Expect<Double> = list.max()

        list = list.min { toBeLessThan(1.3) }
        list = list.max { toBeGreaterThan(4.1) }

        val l3: Expect<Double> = list.last
        list.last { toEqual(4.3) }
    }
}
