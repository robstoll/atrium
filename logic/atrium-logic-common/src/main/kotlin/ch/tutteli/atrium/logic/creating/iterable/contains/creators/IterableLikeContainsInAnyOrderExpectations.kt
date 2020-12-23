@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableLikeContainsInAnyOrderAssertions {

    fun <E, T : IterableLike> values(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : IterableLike> entries(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<out E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion
}
