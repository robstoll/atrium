package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainLineSeparatorsHintTest
import kotlin.test.Test

class CharSequenceToContainLineSeparatorsHintTest : AbstractCharSequenceToContainLineSeparatorsHintTest(
    getToContainPair(),
    getToContainIgnoringCasePair(),
    getNotToContainPair()
) {
    @Test
    fun trigger_run_gutter() = Unit

    companion object : CharSequenceToContainSpecBase() {

        private fun getToContainPair() =
            { what: String -> "$toContain $what" } to
                ("$toContain o $atLeast $value/$values" to Companion::toContainFun)

        private fun toContainFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o atLeast 1 value a
            else expect toContain o atLeast 1 the values(a, *aX)

        private fun getToContainIgnoringCasePair() =
            { what: String -> "$toContain $ignoringCase $what" } to
                ("$toContain o $ignoringCase $value/$values" to Companion::toContainIgnoringCaseFun)

        private fun toContainIgnoringCaseFun(
            expect: Expect<CharSequence>,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> =
            if (aX.isEmpty()) expect toContain o ignoring case value a
            else expect toContain o ignoring case the values(a, *aX)

        private fun getNotToContainPair() =
            { what: String -> "$notToContainValues $what" } to
                (notToContainValues to Companion::notToContainFun)

        private fun notToContainFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>): Expect<CharSequence> =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)
    }
}
