package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

class CharSequenceAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceAssertionsSpec(
    fun0(Expect<CharSequence>::isEmpty),
    fun0(Expect<CharSequence>::isNotEmpty),
    fun0(Expect<CharSequence>::isNotBlank),
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
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.isEmpty()
        a1.isNotEmpty()
        a1.isNotBlank()

        a1.startsWith("expected")
        a1.startsNotWith("expected")
        a1.endsWith("expected")
        a1.endsNotWith("expected")

        a1.startsWith('a')
        a1.startsNotWith('a')
        a1.endsWith('a')
        a1.endsNotWith('a')

        a1.matches(Regex("a"))
        a1.mismatches(Regex("a"))
    }
}
