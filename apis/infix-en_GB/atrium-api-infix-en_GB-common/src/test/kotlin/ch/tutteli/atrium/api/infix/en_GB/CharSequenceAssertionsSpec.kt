package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class CharSequenceAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceAssertionsSpec(
    "toBe ${Empty::class.simpleName}" to ::toBeEmpty,
    "notToBe ${Empty::class.simpleName}" to ::notToBeEmpty,
    "notToBe ${Blank::class.simpleName}" to ::notToBeBlank,
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::startsWith),
    fun1<CharSequence, Char>(Expect<CharSequence>::startsWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::startsNotWith),
    fun1<CharSequence, Char>(Expect<CharSequence>::startsNotWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::endsWith),
    fun1<CharSequence, Char>(Expect<CharSequence>::endsWith),
    fun1<CharSequence, CharSequence>(Expect<CharSequence>::endsNotWith),
    fun1<CharSequence, Char>(Expect<CharSequence>::endsNotWith),
    fun1<CharSequence, Regex>(Expect<CharSequence>::matches),
    fun1<CharSequence, Regex>(Expect<CharSequence>::mismatches)
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1 toBe Empty
        a1 notToBe Empty
        a1 notToBe Blank

        a1 startsWith "expected"
        a1 startsNotWith "expected"
        a1 endsWith "expected"
        a1 endsNotWith "expected"

        a1 startsWith 'a'
        a1 startsNotWith 'a'
        a1 endsWith 'a'
        a1 endsNotWith 'a'

        a1 matches Regex("a")
        a1 mismatches Regex("a")
    }
}

private fun toBeEmpty(expect: Expect<CharSequence>) = expect toBe Empty
private fun notToBeEmpty(expect: Expect<CharSequence>) = expect notToBe Empty
private fun notToBeBlank(expect: Expect<CharSequence>) = expect notToBe Blank
