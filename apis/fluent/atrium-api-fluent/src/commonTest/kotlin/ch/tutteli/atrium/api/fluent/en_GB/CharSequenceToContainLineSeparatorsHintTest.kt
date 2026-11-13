package ch.tutteli.atrium.api.fluent.en_GB

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
                ("$toContain.$value/$values" to Companion::toContainFun)

        private fun toContainFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>): Expect<CharSequence> =
            expect.toContain.atLeast(1).values(a, *aX)

        private fun getToContainIgnoringCasePair() =
            { what: String -> "$toContain $ignoringCase $what" } to
                ("$toContain.$ignoringCase.$value/$values" to Companion::toContainIgnoringCaseFun)

        private fun toContainIgnoringCaseFun(
            expect: Expect<CharSequence>,
            a: Any,
            aX: Array<out Any>
        ): Expect<CharSequence> = expect.toContain.ignoringCase.values(a, *aX)

        private fun getNotToContainPair() =
            { what: String -> "$toContainNot $what" } to
                ("$toContainNot.$value/$values" to Companion::notToContainFun)

        private fun notToContainFun(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>): Expect<CharSequence> =
            expect.notToContain.values(a, *aX)
    }
}
