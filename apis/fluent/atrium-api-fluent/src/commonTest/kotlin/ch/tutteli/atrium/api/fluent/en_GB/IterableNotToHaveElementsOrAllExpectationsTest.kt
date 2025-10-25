package ch.tutteli.atrium.api.fluent.en_GB

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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(2.1))
        var nSet: Expect<Set<Number?>> = expect(setOf(2.1))
        var subList: Expect<ArrayList<out Number>> = expect(arrayListOf(2.1))
        var starSet: Expect<Set<*>> = ch.tutteli.atrium.api.verbs.expect(emptySet<Number>())
        var starCollection: Expect<Collection<*>> = ch.tutteli.atrium.api.verbs.expect(listOf(1.1, "asdf"))

        list = list.notToHaveElementsOrAll {
            toEqual(2.1)
        }
        nSet = nSet.notToHaveElementsOrAll {
            toEqual(2.1)
        }
        subList = subList.notToHaveElementsOrAll {
            toEqual(2.1)
        }
        starSet = starSet.notToHaveElementsOrAll {
            toEqual(2.1)
        }
        starCollection = starCollection.notToHaveElementsOrAll {
            notToEqual(2.1)
        }
    }
}


