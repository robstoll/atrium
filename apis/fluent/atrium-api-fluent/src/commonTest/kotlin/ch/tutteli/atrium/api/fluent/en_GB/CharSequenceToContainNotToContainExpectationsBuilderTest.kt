package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainNotToContainExpectationsTest
import kotlin.test.Test

class CharSequenceToContainNotToContainExpectationsBuilderTest :
    AbstractCharSequenceToContainNotToContainExpectationsTest(
        getToContainPair(),
        getNotToContainPair()
    ) {
    companion object : CharSequenceToContainSpecBase() {
        private fun getToContainPair() = "$toContain.$atLeast(1).$value/$values" to Companion::toContainValues

        private fun toContainValues(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect.toContain.atLeast(1).value(a)
            else expect.toContain.atLeast(1).values(a, *aX)

        private fun getNotToContainPair() =
            "${super.toContainNot}.$value/$values" to Companion::notToContainValues

        private fun notToContainValues(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect.notToContain.value(a)
            else expect.notToContain.values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var a1: Expect<String> = expect("1ac")

        a1 = a1.toContain.atLeast(1).value(1)
        a1 = a1.toContain.atLeast(1).values(1, "a", 'c')
        a1 = a1.notToContain.value(2)
        a1 = a1.notToContain.values(2, "b", 'd')
    }
}
