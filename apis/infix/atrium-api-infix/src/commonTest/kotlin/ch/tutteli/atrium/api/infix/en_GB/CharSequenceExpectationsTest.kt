package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceExpectationsTest
import kotlin.test.Test

class CharSequenceExpectationsTest : AbstractCharSequenceExpectationsTest(
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

    @Test
    fun ambiguityTest() {
        val a1: Expect<String> = expect("Hello my name is Robert")
        val a2: Expect<String> = expect("")

        a2 toBe empty

        a1 notToBe empty
        a1 notToBe blank

        a1 toStartWith "Hello"
        a1 notToStartWith "Robert"
        a1 toEndWith "Robert"
        a1 notToEndWith "Hello"

        a1 toMatch Regex(".+Robert")
        a1 notToMatch Regex("a")
    }
}
