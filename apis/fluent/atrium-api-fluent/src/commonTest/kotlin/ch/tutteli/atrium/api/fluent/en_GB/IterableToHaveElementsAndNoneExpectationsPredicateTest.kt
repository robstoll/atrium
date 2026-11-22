package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToHaveElementsAndNoneExpectationsPredicateTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToHaveElementsAndNoneExpectationsTest(
        fun1(Expect<Iterable<Double>>::toHaveElementsAndNone),
        fun1(Expect<Iterable<Double?>>::toHaveElementsAndNone).withNullableSuffix()
    ) {

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(2.1))
        var nList: Expect<Set<Number?>> = expect(setOf(2.1))
        var subList: Expect<ArrayList<out Number>> = expect(arrayListOf(2.1))
        var star: Expect<Collection<*>> = expect(listOf(1.1, "asdf"))

        list = list.toHaveElementsAndNone {
            toEqual(1.1)
        }
        nList = nList.toHaveElementsAndNone {
            toEqual(1.1)
        }
        subList = subList.toHaveElementsAndNone {
            toEqual(1.1)
        }
        star = star.toHaveElementsAndNone {
            toEqual(2.1)
        }

        nList = expect(setOf<Double?>(2.1))
        star = expect(setOf<Double?>(2.1))

        nList = nList.toHaveElementsAndNone(null)
        star = star.toHaveElementsAndNone(null)
    }
}
