package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToHaveElementsAndAllExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndAllExpectationsSpec(
    fun1(Expect<Iterable<Double>>::toHaveElementsAndAll),
    fun1(Expect<Iterable<Double?>>::toHaveElementsAndAll).withNullableSuffix()
) {

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toHaveElementsAndAll {}
        nList = nList toHaveElementsAndAll {}
        subList = subList toHaveElementsAndAll {}
        star = star toHaveElementsAndAll {}

        nList = nList toHaveElementsAndAll null
        subList = subList toHaveElementsAndAll null
        star = star toHaveElementsAndAll null
    }
}
