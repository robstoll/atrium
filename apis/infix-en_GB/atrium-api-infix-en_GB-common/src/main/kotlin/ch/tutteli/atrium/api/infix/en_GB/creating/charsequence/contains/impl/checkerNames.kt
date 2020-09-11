package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.impl

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep
import kotlin.reflect.KFunction2

internal object StaticName {
    val containsNotValuesFun: String = {
        val f: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> =
            Expect<CharSequence>::containsNot
        //TODO use once  https://youtrack.jetbrains.com/issue/KT-38013 is fixed (there are other places where "values" is hard-coded)
//        val values : KFunction2<Int, Array<out Int>, Values<Int>> = ::values
//        "`${f.name} ${values.name}`"
        "`${f.name} values`"
    }()

    val atLeast = CharSequenceContains.EntryPointStep<*, *>::atLeast.name
    val butAtMost = AtLeastCheckerStep<*, *>::butAtMost.name
    val atMost = CharSequenceContains.EntryPointStep<*, *>::atMost.name
    val exactly = CharSequenceContains.EntryPointStep<*, *>::exactly.name
    val notOrAtMost = CharSequenceContains.EntryPointStep<*, *>::notOrAtMost.name
}
