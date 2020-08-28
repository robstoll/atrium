package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.impl

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerOption
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

internal object StaticName {
    val containsNotValuesFun: String = {
        val f: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> =
            Expect<CharSequence>::containsNot
        //TODO use once  https://youtrack.jetbrains.com/issue/KT-38013 is fixed (there are other places where "values" is hard-coded)
//        val values : KFunction2<Int, Array<out Int>, Values<Int>> = ::values
//        "`${f.name} ${values.name}`"
        "`${f.name} values`"
    }()

    val atLeast = CharSequenceContains.Builder<*, *>::atLeast.name
    val butAtMost = AtLeastCheckerOption<*, *>::butAtMost.name
    val atMost = CharSequenceContains.Builder<*, *>::atMost.name
    val exactly = CharSequenceContains.Builder<*, *>::exactly.name
    val notOrAtMost = CharSequenceContains.Builder<*, *>::notOrAtMost.name
}
