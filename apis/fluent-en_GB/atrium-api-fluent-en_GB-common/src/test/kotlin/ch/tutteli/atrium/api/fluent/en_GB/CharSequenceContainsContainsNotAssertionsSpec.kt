package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented

class CharSequenceContainsContainsNotAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotAssertionsSpec(
        fun2<CharSequence, String, Array<out String>>(Expect<CharSequence>::contains),
        fun2<CharSequence, String, Array<out String>>(Expect<CharSequence>::containsNot),
        "◆ ", "⚬ ", "▶ "
    ) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.contains(1, "a", 'c')
        a1.containsNot(1, "a", 'c')
    }
}
