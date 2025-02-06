package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

class CharSequenceExpectationsSpec : ch.tutteli.atrium.specs.integration.CharSequenceExpectationsSpec(
    fun0(Expect<CharSequence>::toBeEmpty),
    fun0(Expect<CharSequence>::notToBeEmpty),
    fun0(Expect<CharSequence>::notToBeBlank),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::toStartWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::notToStartWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::toEndWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::notToEndWith),
    fun1<CharSequence, Regex>(Expect<CharSequence>::toMatch),
    fun1<CharSequence, Regex>(Expect<CharSequence>::notToMatch)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.toBeEmpty()
        a1.notToBeEmpty()
        a1.notToBeBlank()

        a1.toStartWith("expected")
        a1.notToStartWith("expected")
        a1.toEndWith("expected")
        a1.notToEndWith("expected")

        a1.toMatch(Regex("a"))
        a1.notToMatch(Regex("a"))
    }
}
