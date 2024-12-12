package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableNotToHaveElementsOrNoneExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableNotToHaveElementsOrNoneExpectationsSpec(
    fun1(Expect<Iterable<Double>>::notToHaveElementsOrNone),
    fun1(Expect<Iterable<Double?>>::notToHaveElementsOrNone).withNullableSuffix()
) {

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.notToHaveElementsOrNone {}
        nList = nList.notToHaveElementsOrNone {}
        subList = subList.notToHaveElementsOrNone {}
        star = star.notToHaveElementsOrNone {}

        nList = nList.notToHaveElementsOrNone(null)
        subList = subList.notToHaveElementsOrNone(null)
        star = star.notToHaveElementsOrNone(null)
    }
}
