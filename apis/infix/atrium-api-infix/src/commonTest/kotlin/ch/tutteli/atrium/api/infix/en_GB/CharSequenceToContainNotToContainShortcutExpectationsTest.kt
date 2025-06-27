package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainNotToContainExpectationsTest
import ch.tutteli.atrium.specs.notImplemented
import kotlin.test.Test

class CharSequenceToContainNotToContainShortcutExpectationsTest :
    AbstractCharSequenceToContainNotToContainExpectationsTest(
        getToContainShortcutPair(),
        getNotToContainShortcutPair()
    ) {
    companion object : CharSequenceToContainSpecBase() {

        private fun getToContainShortcutPair() = fun2(Companion::contains)
        private fun getNotToContainShortcutPair() = fun2(Companion::notToContain)

        private fun contains(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain a
            else expect toContain values(a, *aX)

        private fun notToContain(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect notToContain a
            else expect notToContain values(a, *aX)
    }

    @Test
    fun ambiguityTest() {
        val a1: Expect<String> = expect("1ac")

        a1 toContain values(1, "a", 'c')
        a1 notToContain values(2, "b", 'd')
    }
}
