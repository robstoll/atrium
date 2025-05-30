package ch.tutteli.atrium.api.fluent.logic.based.en_GB.creating.iterable.toContain.impl

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import kotlin.reflect.KFunction3

/** @since 1.1.0 */
internal object StaticNames {
    /** @since 1.1.0 */
    val notToContainValuesFun = run {
        val f: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::notToContain
        f.name
    }

    /** @since 1.1.0 */
    val atLeast = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::atLeast.name

    /** @since 1.1.0 */
    val butAtMost = AtLeastCheckerStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::butAtMost.name

    /** @since 1.1.0 */
    val atMost = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::atMost.name

    /** @since 1.1.0 */
    val exactly = IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::exactly.name

    /** @since 1.1.0 */
    val notOrAtMost =
        IterableLikeContains.EntryPointStep<Any?, IterableLike, InAnyOrderSearchBehaviour>::notOrAtMost.name

    /** @since 1.1.0 */
    val notToHaveElementsOrNone = Expect<List<Int>>::notToHaveElementsOrNone.name
}
