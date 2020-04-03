package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction2

internal object StaticName {
    private val f: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> =
        Expect<CharSequence>::containsNot
    val nameContainsNotValuesFun = "`${f.name} values`"
}
