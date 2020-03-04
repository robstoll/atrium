package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.Values
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction2

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> = Expect<CharSequence>::containsNot
    return "`${f.name} ${Values::class.simpleName}`"
}
