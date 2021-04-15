package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class CharSequenceToContainNotToContainExpectationsSpec : Spek({

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceToContainNotToContainExpectationsSpec(
        getToContainPair(),
        getNotToContainPair(),
        "[Atrium][Builder]"
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceToContainNotToContainExpectationsSpec(
        getToContainShortcutPair(),
        getNotToContainShortcutPair(),
        "[Atrium][Shortcut]"
    ) {})
}) {
    companion object : CharSequenceToContainSpecBase() {

        private fun getToContainPair() = "$toContain o $value/$values" to Companion::toContainBuilder

        private fun toContainBuilder(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect toContain o atLeast 1 value a
            else expect toContain o atLeast 1 the values(a, *aX)

        private fun getNotToContainPair() = "$notToContain o  $value/$values" to Companion::notToContainBuilder
        private fun notToContainBuilder(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)

        private fun getToContainShortcutPair() = fun2<CharSequence, Any, Array<out Any>>(Companion::contains)
        private fun getNotToContainShortcutPair() = fun2<CharSequence, Any, Array<out Any>>(Companion::notToContain)

        private fun contains(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains a
            else expect contains values(a, *aX)

        private fun notToContain(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect notToContain a
            else expect notToContain values(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1 contains values(1, "a", 'c')
        a1 notToContain values(1, "a", 'c')
    }
}
