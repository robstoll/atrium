package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1

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
    fun1<CharSequence, Regex>(Expect<CharSequence>::matches)
)
