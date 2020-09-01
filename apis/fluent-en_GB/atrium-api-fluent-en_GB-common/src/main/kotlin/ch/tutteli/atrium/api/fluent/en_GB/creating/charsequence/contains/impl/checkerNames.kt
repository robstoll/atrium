package ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.impl

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep
import kotlin.reflect.KFunction3

internal object StaticName {
    val containsNotValuesFun = {
        val f: KFunction3<Expect<CharSequence>, Any, Array<out Any>, Expect<CharSequence>> =
            Expect<CharSequence>::containsNot
        f.name
    }()

    val atLeast = CharSequenceContains.EntryPointStep<*, *>::atLeast.name
    val butAtMost = AtLeastCheckerStep<*, *>::butAtMost.name
    val atMost = CharSequenceContains.EntryPointStep<*, *>::atMost.name
    val exactly = CharSequenceContains.EntryPointStep<*, *>::exactly.name
    val notOrAtMost = CharSequenceContains.EntryPointStep<*, *>::notOrAtMost.name
}
