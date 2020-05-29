package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.notImplemented

class IteratorFeatureAssertionSpec : ch.tutteli.atrium.specs.integration.IteratorFeatureAssertionSpec(
    fun0(Expect<Iterator<Int>>::hasNext),
    fun0(Expect<Iterator<Int>>::hasNotNext)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()

        a1 = a1.hasNext()
        a1 = a1.hasNotNext()
    }
}
