package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

class CharSequenceExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractCharSequenceExpectationsTest(
    "toBe ${empty::class.simpleName}" to Companion::toBeEmpty,
    "notToBe ${empty::class.simpleName}" to Companion::notToBeEmpty,
    "notToBe ${blank::class.simpleName}" to Companion::notToBeBlank,
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::toStartWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::notToStartWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::toEndWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::notToEndWith),
    fun1<CharSequence, Regex>(Expect<CharSequence>::toMatch),
    fun1<CharSequence, Regex>(Expect<CharSequence>::notToMatch)
) {
    companion object {
        private fun toBeEmpty(expect: Expect<CharSequence>) = expect toBe empty
        private fun notToBeEmpty(expect: Expect<CharSequence>) = expect notToBe empty
        private fun notToBeBlank(expect: Expect<CharSequence>) = expect notToBe blank
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1 toBe empty
        a1 notToBe empty
        a1 notToBe blank

        a1 toStartWith "expected"
        a1 notToStartWith "expected"
        a1 toEndWith "expected"
        a1 notToEndWith "expected"

        a1 toMatch Regex("a")
        a1 notToMatch Regex("a")
    }
}
