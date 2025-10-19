package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractCharSequenceExpectationsTest
import kotlin.test.Test

class CharSequenceExpectationsTest : AbstractCharSequenceExpectationsTest(
    fun0(Expect<CharSequence>::toBeEmpty),
    fun0(Expect<CharSequence>::notToBeEmpty),
    fun0(Expect<CharSequence>::notToBeBlank),
    fun1(Expect<CharSequence>::toStartWith),
    fun1(Expect<CharSequence>::notToStartWith),
    fun1(Expect<CharSequence>::toEndWith),
    fun1(Expect<CharSequence>::notToEndWith),
    fun1(Expect<CharSequence>::toMatch),
    fun1(Expect<CharSequence>::notToMatch)
) {

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var a1: Expect<String> = expect("Hello my name is Robert")
        var a2: Expect<String> = expect("")

        a2 = a2.toBeEmpty()

        a1 = a1.notToBeEmpty()
        a1 = a1.notToBeBlank()

        a1 = a1.toStartWith("Hello")
        a1 = a1.notToStartWith("Robert")
        a1 = a1.toEndWith("Robert")
        a1 = a1.notToEndWith("Hello")

        a1 = a1.toMatch(Regex(".+Robert"))
        a1 = a1.notToMatch(Regex("a"))
    }
}
