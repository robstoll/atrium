package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.notImplemented

class IteratorExpectationsSpec : ch.tutteli.atrium.specs.integration.IteratorExpectationsSpec(
    fun0(Expect<Iterator<Int>>::toHaveNext),
    fun0(Expect<Iterator<Int>>::notToHaveNext)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()

        a1 = a1.toHaveNext()
        a1 = a1.notToHaveNext()
    }
}
