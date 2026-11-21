package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

class IteratorExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractIteratorExpectationsTest(
    fun0(Expect<Iterator<Int>>::toHaveNext),
    fun0(Expect<Iterator<Int>>::notToHaveNext),
    feature0(Expect<Iterator<Int>>::next),
    fun1(Expect<Iterator<Int>>::next),
    feature0(Expect<Iterator<Int?>>::next).withNullableSuffix(),
    fun1(Expect<Iterator<Int?>>::next).withNullableSuffix(),
) {

    @Suppress("UNUSED_VALUE", "UNUSED_VARIABLE", "unused", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = expect(listOf(1.0).iterator())
        var a1b: Expect<Iterator<Double>> = expect(emptyList<Double>().iterator())
        var a1c: Expect<Iterator<Double>> = expect(listOf(1.0).iterator())
        var a2: Expect<Double>

        a1 = a1.toHaveNext()
        a1b = a1b.notToHaveNext()
        a1 = a1.next { toEqual(1.0) }

        a2 = a1c.next()
    }
}
