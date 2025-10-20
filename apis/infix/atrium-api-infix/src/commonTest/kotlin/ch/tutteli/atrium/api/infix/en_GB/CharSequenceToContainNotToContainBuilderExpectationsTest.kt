package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainNotToContainExpectationsTest
import kotlin.test.Test

class CharSequenceToContainNotToContainBuilderExpectationsTest :
    AbstractCharSequenceToContainNotToContainExpectationsTest(
        getToContainPair(),
        getNotToContainPair()
    ) {

    companion object : CharSequenceToContainSpecBase() {

        private fun getToContainPair() = "$toContain o $value/$values" to Companion::toContainBuilder

        private fun toContainBuilder(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain o atLeast 1 value a
            else expect toContain o atLeast 1 the values(a, *aX)

        private fun getNotToContainPair() = "$notToContain o  $value/$values" to Companion::notToContainBuilder
        private fun notToContainBuilder(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var a1: Expect<String> = expect("1ac")

        a1 = a1 toContain o atLeast 1 value 1
        a1 = a1 toContain o atLeast 1 the values(1, "a", 'c')
        a1 = a1 notToContain o value 2
        a1 = a1 notToContain o the values(2, "b", 'd')
    }
}
