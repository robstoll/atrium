package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableNotToHaveElementsOrNoneExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableNotToHaveElementsOrNoneExpectationsTest(
        fun1(Expect<Iterable<Double>>::notToHaveElementsOrNone),
        fun1(Expect<Iterable<Double?>>::notToHaveElementsOrNone).withNullableSuffix()
    ) {

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var nList: Expect<List<Number>> = expect(listOf(1.1))
        var nSet: Expect<Set<Number?>> = expect(setOf(null, 1.1))
        var subList: Expect<ArrayList<out Number>> = expect(arrayListOf(1.1))
        var nStar: Expect<Collection<*>> = expect(listOf(1.1, "asdf"))

        nList = nList.notToHaveElementsOrNone {
            toEqual(2.1)
        }
        nSet = nSet.notToHaveElementsOrNone {
            toEqual(2.1)
        }
        subList = subList.notToHaveElementsOrNone {
            notToEqual(1.1)
        }
        nStar = nStar.notToHaveElementsOrNone {
            toEqual(2.1)
        }

        nList = nList.notToHaveElementsOrNone(null)
        subList = subList.notToHaveElementsOrNone(null)
        nStar = nStar.notToHaveElementsOrNone(null)
    }
}
