@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction2

internal object StaticName {
    val nameContainsNotValuesFun: String = {
        val f: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> =
            Expect<CharSequence>::containsNot
        //TODO use once  https://youtrack.jetbrains.com/issue/KT-38013 is fixed (there are other places where "values" is hard-coded)
//        val values : KFunction2<Int, Array<out Int>, Values<Int>> = ::values
//        "`${f.name} ${values.name}`"
        "`${f.name} values`"
    }()
}
