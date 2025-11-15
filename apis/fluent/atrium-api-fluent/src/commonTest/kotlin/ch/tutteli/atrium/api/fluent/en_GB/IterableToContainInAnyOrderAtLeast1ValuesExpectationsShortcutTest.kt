package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1ValuesExpectationsShortcutTest :
    AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
        fun2<Iterable<Double>, Double, Array<out Double>>(Expect<Iterable<Double>>::toContain),
        fun2<Iterable<Double?>, Double?, Array<out Double?>>(Expect<Iterable<Double?>>::toContain)
    ) {

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2, 2.2))
        var star: Expect<Collection<*>> = expect(listOf(1, null, 1.2, "asdf"))

        list = list.toContain { toEqual(1) }
        nSet = nSet.toContain { toEqual(1) }
        subList = subList.toContain { toEqual(1) }
        star = star.toContain { toEqual(1) }

        list = list.toContain(1, 1.2)
        nSet = nSet.toContain(1, 1.2)
        subList = subList.toContain(1, 2.2)
        star = star.toContain(1, 1.2, "asdf")
    }
}
