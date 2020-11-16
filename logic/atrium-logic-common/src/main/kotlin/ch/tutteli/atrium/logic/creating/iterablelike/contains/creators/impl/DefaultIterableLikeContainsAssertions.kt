package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.IterableLikeContainsAssertions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

class DefaultIterableLikeContainsAssertions : IterableLikeContainsAssertions {
    override fun <E, T : IterableLike> valuesInAnyOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion =
        createAssertionGroupWithoutChecker(entryPointStepLogic, expected, ::InAnyOrderOnlyValuesAssertionCreator)

    override fun <E : Any, T : IterableLike> entriesInAnyOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion = createAssertionGroupWithoutChecker(
        entryPointStepLogic, assertionCreators, ::InAnyOrderOnlyEntriesAssertionCreator
    )


    override fun <E, T : IterableLike> valuesInOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion =
        createAssertionGroupWithoutChecker(entryPointStepLogic, expected, ::InOrderOnlyValuesAssertionCreator)

    override fun <E : Any, T : IterableLike> entriesInOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion = createAssertionGroupWithoutChecker(
        entryPointStepLogic, assertionCreators, ::InOrderOnlyEntriesAssertionCreator
    )


    override fun <E, T : IterableLike> valuesInOrderOnlyGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<E>>
    ): Assertion = createAssertionGroupWithoutChecker(
            entryPointStepLogic, groups, ::InOrderOnlyGroupedValuesAssertionCreator
    )

    override fun <E : Any, T : IterableLike> entriesInOrderOnlyGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(Expect<E>.() -> Unit)?>>
    ): Assertion = createAssertionGroupWithoutChecker(
            entryPointStepLogic, groups, ::InOrderOnlyGroupedEntriesAssertionCreator
    )

    private fun <E, T : IterableLike, SC, S : IterableLikeContains.SearchBehaviour> createAssertionGroupWithoutChecker(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>,
        expected: List<SC>,
        factory: ((T) -> Iterable<E>, S) -> IterableLikeContains.Creator<T, SC>
    ): AssertionGroup {
        val creator = factory(entryPointStepLogic.converter, entryPointStepLogic.searchBehaviour)
        return creator.createAssertionGroup(entryPointStepLogic.container, expected)
    }
}
