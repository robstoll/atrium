//TODO rename package to iterablelike.toContain with 1.0.0
package ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.impl

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import kotlin.reflect.KFunction3

internal object StaticNames {
    val notToContainValuesFun = run {
        val f: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::notToContain
        f.name
    }

    val atLeast = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    val butAtMost = AtLeastCheckerStep<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    val atMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atMost.name
    val exactly = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::exactly.name
    val notOrAtMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name

    /** @since 0.19.0 */
    val notToHaveElementsOrNone = Expect<List<Int>>::notToHaveElementsOrNone.name
}
