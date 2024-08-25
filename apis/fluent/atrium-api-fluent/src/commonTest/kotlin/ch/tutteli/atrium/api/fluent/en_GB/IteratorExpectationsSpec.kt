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

    @Suppress("unused", "UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()
        var a2: Expect<Double> = notImplemented()

        a1 = a1.toHaveNext()
        a1 = a1.notToHaveNext()
        a1 = a1.next {  }

        a2 = a1.next()
    }
}
