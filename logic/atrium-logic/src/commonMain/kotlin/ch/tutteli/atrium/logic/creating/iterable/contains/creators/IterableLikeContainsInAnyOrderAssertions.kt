//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain logic of Atrium has to provide.
 */
interface IterableLikeContainsInAnyOrderAssertions {

    fun <E, T : IterableLike> values(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>,
        notToHaveNextOrNoneFunName: String
    ): Assertion

    fun <E : Any, T : IterableLike> entries(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<out E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>,
        notToHaveNextOrNoneFunName: String
    ): Assertion
}


