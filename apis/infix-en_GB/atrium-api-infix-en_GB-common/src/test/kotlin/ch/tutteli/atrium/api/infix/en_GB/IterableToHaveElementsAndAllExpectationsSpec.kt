package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToHaveElementsAndAllExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndAllExpectationsSpec(
    fun1(Expect<Iterable<Double>>::toHaveElementsAndAll),
    fun1(Expect<Iterable<Double?>>::toHaveElementsAndAll).withNullableSuffix()
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()

        var star: Expect<Iterable<*>> = notImplemented()

        a1 = a1 toHaveElementsAndAll {}

        a1b = a1b toHaveElementsAndAll {}
        a1b = a1b toHaveElementsAndAll null

        star = star toHaveElementsAndAll {}
    }
}
