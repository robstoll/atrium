package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction2

class CharSequenceAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    Expect<CharSequence>::isEmpty.name to Expect<CharSequence>::isEmpty,
    Expect<CharSequence>::isNotEmpty.name to Expect<CharSequence>::isNotEmpty,
    Expect<CharSequence>::isNotBlank.name to Expect<CharSequence>::isNotBlank,
    startsWith.name to startsWith,
    startWithCharName to startsWithChar,
    startsNotWith.name to startsNotWith,
    startNotWithCharName to startsNotWithChar,
    endsWith.name to endsWith,
    endsWithCharName to endsWithChar,
    endsNotWith.name to endsNotWith,
    endsNotWithCharName to endsNotWithChar
) {
    companion object {
        const val CHAR_SUFFIX = "Char"

        val startsWith: KFunction2<Expect<CharSequence>, CharSequence, Expect<CharSequence>> = Expect<CharSequence>::startsWith
        val startsWithChar: KFunction2<Expect<CharSequence>, Char, Expect<CharSequence>> = Expect<CharSequence>::startsWith
        val startWithCharName = startsWith.name + CHAR_SUFFIX

        val startsNotWith: KFunction2<Expect<CharSequence>, CharSequence, Expect<CharSequence>> = Expect<CharSequence>::startsNotWith
        val startsNotWithChar: KFunction2<Expect<CharSequence>, Char, Expect<CharSequence>> = Expect<CharSequence>::startsNotWith
        val startNotWithCharName = startsNotWith.name + CHAR_SUFFIX

        val endsWith: KFunction2<Expect<CharSequence>, CharSequence, Expect<CharSequence>> = Expect<CharSequence>::endsWith
        val endsWithChar: KFunction2<Expect<CharSequence>, Char, Expect<CharSequence>> = Expect<CharSequence>::endsWith
        val endsWithCharName = endsWithChar.name + CHAR_SUFFIX

        val endsNotWith: KFunction2<Expect<CharSequence>, CharSequence, Expect<CharSequence>> = Expect<CharSequence>::endsNotWith
        val endsNotWithChar: KFunction2<Expect<CharSequence>, Char, Expect<CharSequence>> = Expect<CharSequence>::endsNotWith
        val endsNotWithCharName = endsNotWith.name + CHAR_SUFFIX
    }
}
