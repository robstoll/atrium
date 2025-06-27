package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceToContainNotToContainExpectationsTest
import ch.tutteli.atrium.specs.notImplemented
import kotlin.test.Test

class CharSequenceToContainNotToContainExpectationsShortcutTest :
    AbstractCharSequenceToContainNotToContainExpectationsTest(
        getToContainShortcutPair(),
        getNotToContainShortcutPair()
    ) {
    companion object : CharSequenceToContainSpecBase() {

        private fun getToContainShortcutPair() =
            fun2(Expect<CharSequence>::toContain)

        private fun getNotToContainShortcutPair() =
            fun2(Expect<CharSequence>::notToContain)
    }

    @Test
    fun ambiguityTest() {
        val a1: Expect<String> = expect("1ac")

        a1.toContain(1, "a", 'c')
        a1.notToContain(2, "b", 'd')
    }
}
