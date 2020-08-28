package ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.impl

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerOption
import kotlin.reflect.KFunction3

internal object StaticName {
    val containsNotValuesFun = {
        val f: KFunction3<Expect<CharSequence>, Any, Array<out Any>, Expect<CharSequence>> =
            Expect<CharSequence>::containsNot
        f.name
    }()

    val atLeast = CharSequenceContains.Builder<*, *>::atLeast.name
    val butAtMost = AtLeastCheckerOption<*, *>::butAtMost.name
    val atMost = CharSequenceContains.Builder<*, *>::atMost.name
    val exactly = CharSequenceContains.Builder<*, *>::exactly.name
    val notOrAtMost = CharSequenceContains.Builder<*, *>::notOrAtMost.name
}
