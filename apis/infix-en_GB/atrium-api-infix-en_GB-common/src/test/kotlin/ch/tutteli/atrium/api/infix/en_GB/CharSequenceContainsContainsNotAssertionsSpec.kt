package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class CharSequenceContainsContainsNotAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotAssertionsSpec(
        fun2<CharSequence, String, Array<out String>>(Companion::contains),
        fun2<CharSequence, String, Array<out String>>(Companion::containsNot),
        "* ", "- ", ">> "
    ) {
    companion object : WithAsciiReporter() {

        private fun contains(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains a
            else expect contains Values(a, *aX)

        private fun containsNot(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect containsNot a
            else expect containsNot Values(a, *aX)

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1 contains Values(1, "a", 'c')
        a1 containsNot Values(1, "a", 'c')
    }
}
