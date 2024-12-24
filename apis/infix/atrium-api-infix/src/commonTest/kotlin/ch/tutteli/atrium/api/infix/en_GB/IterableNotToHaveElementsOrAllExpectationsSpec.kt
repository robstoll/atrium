package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableNotToHaveElementsOrAllExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableNotToHaveElementsOrAllExpectationsSpec(
    fun1(Expect<Iterable<Double>>::notToHaveElementsOrAll),
    fun1(Expect<Iterable<Double?>>::notToHaveElementsOrAll).withNullableSuffix()
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list notToHaveElementsOrAll {}
        nList = nList notToHaveElementsOrAll {}
        subList = subList notToHaveElementsOrAll {}
        star = star notToHaveElementsOrAll {}

        nList = nList notToHaveElementsOrAll null
        subList = subList notToHaveElementsOrAll null
        star = star notToHaveElementsOrAll null
    }
}
