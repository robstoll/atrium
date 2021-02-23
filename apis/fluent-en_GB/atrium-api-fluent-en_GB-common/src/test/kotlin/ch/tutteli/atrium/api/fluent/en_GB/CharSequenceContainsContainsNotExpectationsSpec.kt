package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class CharSequenceContainsContainsNotExpectationsSpec : Spek({

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotExpectationsSpec(
        getContainsPair(),
        getContainsNotPair(),
        "[Atrium][Builder]"
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotExpectationsSpec(
        getContainsShortcutPair(),
        getContainsNotShortcutPair(),
        "[Atrium][Shortcut]"
    ) {})
}) {
    companion object : CharSequenceContainsSpecBase() {
        private fun getContainsPair() = "$contains.$atLeast(1).$value/$values" to Companion::containsValues

        private fun containsValues(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect.contains.atLeast(1).value(a)
            else expect.contains.atLeast(1).values(a, *aX)

        private fun getContainsNotPair() =
            "${super.containsNot}.$value/$values" to Companion::containsNotValues

        private fun containsNotValues(expect: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            if (aX.isEmpty()) expect.containsNot.value(a)
            else expect.containsNot.values(a, *aX)

        private fun getContainsShortcutPair() =
            fun2<CharSequence, Any, Array<out Any>>(Expect<CharSequence>::contains)

        private fun getContainsNotShortcutPair() =
            fun2<CharSequence, Any, Array<out Any>>(Expect<CharSequence>::containsNot)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.contains(1, "a", 'c')
        a1.containsNot(1, "a", 'c')
    }
}
