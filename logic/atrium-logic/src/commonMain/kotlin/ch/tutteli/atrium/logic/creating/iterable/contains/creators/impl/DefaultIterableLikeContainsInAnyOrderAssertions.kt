package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.IterableLikeContainsInAnyOrderAssertions
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

class DefaultIterableLikeContainsInAnyOrderAssertions : IterableLikeContainsInAnyOrderAssertions {
    override fun <E, T : IterableLike> values(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>,
        notToHaveNextOrNoneFunName: String
    ): Assertion = createAssertionGroup(
        checkerStepLogic, expected, notToHaveNextOrNoneFunName, ::InAnyOrderValuesAssertionCreator
    )

    override fun <E : Any, T : IterableLike> entries(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<out E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>,
        notToHaveNextOrNoneFunName: String
    ): Assertion = createAssertionGroup(
        checkerStepLogic, assertionCreators, notToHaveNextOrNoneFunName, ::InAnyOrderEntriesAssertionCreator
    )

    private fun <E, T : IterableLike, SC, S : IterableLikeContains.SearchBehaviour> createAssertionGroup(
        checkerStepLogic: IterableLikeContains.CheckerStepLogic<E, T, S>,
        expected: List<SC>,
        notToHaveNextOrNoneFunName: String,
        factory: ((T) -> Iterable<E>, S, List<IterableLikeContains.Checker>, String) -> IterableLikeContains.Creator<T, SC>
    ): AssertionGroup {
        val creator = factory(
            checkerStepLogic.entryPointStepLogic.converter,
            checkerStepLogic.entryPointStepLogic.searchBehaviour,
            checkerStepLogic.checkers,
            notToHaveNextOrNoneFunName
        )
        return creator.createAssertionGroup(checkerStepLogic.entryPointStepLogic.container, expected)
    }
}
