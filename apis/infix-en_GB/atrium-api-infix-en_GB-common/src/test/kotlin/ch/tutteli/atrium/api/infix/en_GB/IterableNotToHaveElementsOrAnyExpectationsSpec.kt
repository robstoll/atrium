package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableNotToHaveElementsOrAnyExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableNotToHaveElementsOrAnyExpectationsSpec(
    fun1(Expect<Iterable<Double>>::notToHaveElementsOrAny),
    fun1(Expect<Iterable<Double?>>::notToHaveElementsOrAny).withNullableSuffix()
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list notToHaveElementsOrAny {}
        nList = nList notToHaveElementsOrAny {}
        subList = subList notToHaveElementsOrAny {}
        star = star notToHaveElementsOrAny {}

        nList = nList notToHaveElementsOrAny null
        subList = subList notToHaveElementsOrAny null
        star = star notToHaveElementsOrAny null
    }
}
