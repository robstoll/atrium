package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableNotToHaveElementsOrAnyExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableNotToHaveElementsOrAnyExpectationsTest(
        fun1(Expect<Iterable<Double>>::notToHaveElementsOrAny),
        fun1(Expect<Iterable<Double?>>::notToHaveElementsOrAny).withNullableSuffix()
    ) {

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1.1))
        var nSet: Expect<Set<Number?>> = expect(setOf(null, 1.1))
        var subList: Expect<ArrayList<out Number>> = expect(arrayListOf(1.1))
        var star: Expect<Collection<*>> = expect(listOf(1.1, "asdf"))

        list = list notToHaveElementsOrAny {
            it notToEqual 2.1
        }
        nSet = nSet notToHaveElementsOrAny {
            it notToEqual 2.1
        }
        subList = subList notToHaveElementsOrAny {
            it toEqual 1.1
        }
        star = star notToHaveElementsOrAny {
            it notToEqual 2.1
        }

        list = list notToHaveElementsOrNone null
        subList = subList notToHaveElementsOrNone null
        star = star notToHaveElementsOrNone null
    }
}
