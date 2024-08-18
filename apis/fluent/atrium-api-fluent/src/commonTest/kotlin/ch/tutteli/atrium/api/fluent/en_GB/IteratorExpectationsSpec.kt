package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

class IteratorExpectationsSpec : ch.tutteli.atrium.specs.integration.IteratorExpectationsSpec(
    fun0(Expect<Iterator<Int>>::toHaveNext),
    fun0(Expect<Iterator<Int>>::notToHaveNext),
    feature0(Expect<Iterator<Int>>::next),
    fun1(Expect<Iterator<Int>>::next),
    feature0(Expect<Iterator<Int?>>::next).withNullableSuffix(),
    fun1(Expect<Iterator<Int?>>::next).withNullableSuffix(),
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()

        a1 = a1.toHaveNext()
        a1 = a1.notToHaveNext()
    }
}
