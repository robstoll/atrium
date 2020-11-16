package ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.impl

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.AtLeastCheckerStep
import kotlin.reflect.KFunction3

internal object StaticName {
    val containsNotValuesFun = {
        val f: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsNot
        f.name
    }()

    val atLeast = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    val butAtMost = AtLeastCheckerStep<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    val atMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atMost.name
    val exactly = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::exactly.name
    val notOrAtMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name
}
