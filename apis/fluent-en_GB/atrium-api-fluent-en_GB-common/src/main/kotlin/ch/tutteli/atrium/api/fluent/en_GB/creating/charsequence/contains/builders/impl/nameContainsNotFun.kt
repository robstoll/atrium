package ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction3

internal fun nameContainsNotValuesFun(): String {
    val f: KFunction3<Expect<CharSequence>, Any, Array<out Any>, Expect<CharSequence>> = Expect<CharSequence>::containsNot
    return f.name
}
