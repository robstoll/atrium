package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class CharSequenceContainsContainsNotAssertionsSpec : Spek({

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotAssertionsSpec(
        getContainsPair(),
        getContainsNotPair(),
        "* ", "- ", ">> ",
        "[Atrium][Builder]"
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNotShortcutPair(),
        "* ", "- ", ">> ",
        "[Atrium][Shortcut]"
    ) {})
}) {
    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsPair() = "$contains o $atLeast 1 value/the Values" to Companion::containsBuilder

        private fun containsBuilder(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains o atLeast 1 value a
            else expect contains o atLeast 1 the Values(
                a,
                *aX
            )

        private fun getContainsNotPair() = "$containsNot o $atLeast 1 value/the Values" to Companion::containsNotBuilder
        private fun containsNotBuilder(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect containsNot o value a
            else expect containsNot o the Values(
                a,
                *aX
            )

        private fun getContainsShortcutPair() = fun2<CharSequence, Any, Array<out Any>>(Companion::contains)
        private fun getContainsNotShortcutPair() = fun2<CharSequence, Any, Array<out Any>>(Companion::containsNot)

        private fun contains(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect contains a
            else expect contains Values(
                a,
                *aX
            )

        private fun containsNot(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect containsNot a
            else expect containsNot Values(
                a,
                *aX
            )
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1 contains Values(1, "a", 'c')
        a1 containsNot Values(1, "a", 'c')
    }
}
