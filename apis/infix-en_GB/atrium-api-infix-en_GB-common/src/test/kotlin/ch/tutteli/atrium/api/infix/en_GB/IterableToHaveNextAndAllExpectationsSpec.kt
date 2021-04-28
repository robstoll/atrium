package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToHaveNextAndAllExpectationsSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndAllExpectationsSpec(
    fun1(Expect<Iterable<Double>>::toHaveNextAndAll),
    fun1(Expect<Iterable<Double?>>::toHaveNextAndAll).withNullableSuffix()
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()

        var star: Expect<Iterable<*>> = notImplemented()

        a1 = a1 toHaveNextAndAll {}

        a1b = a1b toHaveNextAndAll {}
        a1b = a1b toHaveNextAndAll null

        star = star toHaveNextAndAll {}
    }
}
