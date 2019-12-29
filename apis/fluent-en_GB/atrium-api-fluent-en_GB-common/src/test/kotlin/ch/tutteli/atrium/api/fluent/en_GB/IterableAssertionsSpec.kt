package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.notImplemented

object IterableAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAssertionsSpec(
    fun0(Expect<Iterable<Int>>::hasNext),
    fun0(Expect<Iterable<Int>>::hasNotNext)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterable<Double>> = notImplemented()
        var a2: Expect<out Iterable<Double>> = notImplemented()
        var a1b: Expect<Iterable<Double?>> = notImplemented()
        var a2b: Expect<out Iterable<Double?>> = notImplemented()

        var a3: Expect<out Iterable<*>> = notImplemented()

        a1 = a1.hasNext()
        a2 = a2.hasNext()
        a1 = a1.hasNotNext()
        a2 = a2.hasNotNext()

        a1b = a1b.hasNext()
        a2b = a2b.hasNext()
        a1b = a1b.hasNotNext()
        a2b = a2b.hasNotNext()

        a3 = a3.hasNext()
        a3 = a3.hasNotNext()
    }
}
