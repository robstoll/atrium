package ch.tutteli.atrium.api.fluent.logic.based.en_GB

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
        private fun getToContainPair() = "$toContain.$atLeast(1).$value/$values" to Companion::toContainValues

        private fun toContainValues(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect.toContain.atLeast(1).value(a)
            else expect.toContain.atLeast(1).values(a, *aX)

        private fun getNotToContainPair() =
            "${super.toContainNot}.$value/$values" to Companion::notToContainValues

        private fun notToContainValues(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect.notToContain.value(a)
            else expect.notToContain.values(a, *aX)

        private fun getToContainShortcutPair() =
            fun2<CharSequence, Any, Array<out Any>>(Expect<CharSequence>::toContain)

        private fun getNotToContainShortcutPair() =
            fun2<CharSequence, Any, Array<out Any>>(Expect<CharSequence>::notToContain)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.toContain(1, "a", 'c')
        a1.notToContain(1, "a", 'c')
    }
}
