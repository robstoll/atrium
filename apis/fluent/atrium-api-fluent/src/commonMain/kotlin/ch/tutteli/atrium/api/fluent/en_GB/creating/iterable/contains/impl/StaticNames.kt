package ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.impl

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import kotlin.reflect.KFunction3

@Deprecated(
    "Use the one from package toContain, will be removed with 1.2.0",
    ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticNames")
)
internal object StaticNames {
    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.notToContainValuesFun")
    )
    val notToContainValuesFun = run {
        val f: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::notToContain
        f.name
    }

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.atLeast")
    )
    val atLeast = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::atLeast.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.butAtMost")
    )
    val butAtMost = AtLeastCheckerStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::butAtMost.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.atMost")
    )
    val atMost = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::atMost.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.exactly")
    )
    val exactly = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::exactly.name

    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.notOrAtMost")
    )
    val notOrAtMost =
        IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::notOrAtMost.name

    /** @since 1.0.0 */
    @Deprecated(
        "Use the one from package toContain, will be removed with 1.2.0",
        ReplaceWith("ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticName.notToHaveElementsOrNone")
    )
    val notToHaveElementsOrNone = Expect<List<Int>>::notToHaveElementsOrNone.name
}
