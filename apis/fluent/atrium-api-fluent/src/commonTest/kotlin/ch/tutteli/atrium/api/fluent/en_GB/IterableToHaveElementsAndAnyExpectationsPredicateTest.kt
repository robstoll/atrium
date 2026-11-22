package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToHaveElementsAndAnyExpectationsPredicateTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToHaveElementsAndAnyExpectationsTest(
        fun1(Expect<Iterable<Double>>::toHaveElementsAndAny),
        fun1(Expect<Iterable<Double?>>::toHaveElementsAndAny).withNullableSuffix()
    ) {

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(2.1))
        var nList: Expect<Set<Number?>> = expect(setOf(2.1))
        var subList: Expect<ArrayList<out Number>> = expect(arrayListOf(2.1))
        var star: Expect<Collection<*>> = expect(listOf(1.1, "asdf"))

        list = list.toHaveElementsAndAny {
            toEqual(2.1)
        }
        nList = nList.toHaveElementsAndAny {
            toEqual(2.1)
        }
        subList = subList.toHaveElementsAndAny {
            toEqual(2.1)
        }
        star = star.toHaveElementsAndAny {
            notToEqual(2.1)
        }

        nList = expect(setOf<Double?>(null))
        star = expect(setOf<Double?>(null))

        nList = nList.toHaveElementsAndAny(null)
        star = star.toHaveElementsAndAny(null)
    }
}
