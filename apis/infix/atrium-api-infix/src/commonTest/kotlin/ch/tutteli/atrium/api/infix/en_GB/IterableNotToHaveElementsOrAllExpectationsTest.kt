package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableNotToHaveElementsOrAllExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableNotToHaveElementsOrAllExpectationsTest(
        fun1(Expect<Iterable<Double>>::notToHaveElementsOrAll),
        fun1(Expect<Iterable<Double?>>::notToHaveElementsOrAll).withNullableSuffix()
    ) {


    @Suppress("AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, 1.2))
        var subList: Expect<ArrayList<out Number>> = expect(arrayListOf(1, 1.2))
        var starSet: Expect<Set<*>> = ch.tutteli.atrium.api.verbs.expect(emptySet<Number>())
        var starCollection: Expect<Collection<*>> = expect(listOf(1, "asdf"))

        list = list notToHaveElementsOrAll {
            it notToEqual 2
        }
        nSet = nSet notToHaveElementsOrAll {
            it notToEqual 2
        }
        subList = subList notToHaveElementsOrAll {
            it notToEqual 2
        }
        starSet = starSet.notToHaveElementsOrAll {
            toEqual(2.1)
        }
        starCollection = starCollection notToHaveElementsOrAll {
            it notToEqual 2
        }
    }
}
