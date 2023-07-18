package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.impl

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep
import kotlin.reflect.KFunction2

@Deprecated(
    "Use the one from package toContain, will be removed with 1.2.0",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticNames")
)
internal object StaticNames {
    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticName.notToContainValuesFun")
    )
    val notToContainValuesFun = run {
        val f: KFunction2<Expect<CharSequence>, Values<Any>, Expect<CharSequence>> =
            Expect<CharSequence>::notToContain
        "`${f.name} values`"
    }

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticName.atLeast")
    )
    val atLeast = CharSequenceContains.EntryPointStep<CharSequence, *>::atLeast.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticName.butAtMost")
    )
    val butAtMost = AtLeastCheckerStep<CharSequence, *>::butAtMost.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticName.atMost")
    )
    val atMost = CharSequenceContains.EntryPointStep<CharSequence, *>::atMost.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticName.exactly")
    )
    val exactly = CharSequenceContains.EntryPointStep<CharSequence, *>::exactly.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.creating.charSequence.toContain.impl.StaticName.notOrAtMost")
    )
    val notOrAtMost = CharSequenceContains.EntryPointStep<CharSequence, *>::notOrAtMost.name
}
