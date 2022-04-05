package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.IterableLikeContainsAssertions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

class DefaultIterableLikeContainsAssertions : IterableLikeContainsAssertions {
    override fun <E, T : IterableLike> valuesInAnyOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>,
        reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion = createAssertionGroupWithoutCheckerInAnyOrder(
        entryPointStepLogic, expected, reportingOptions, ::InAnyOrderOnlyValuesAssertionCreator
    )

    override fun <E : Any, T : IterableLike> entriesInAnyOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>,
        reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion = createAssertionGroupWithoutCheckerInAnyOrder(
        entryPointStepLogic, assertionCreators, reportingOptions, ::InAnyOrderOnlyEntriesAssertionCreator
    )


    override fun <E, T : IterableLike> valuesInOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit
    ): Assertion = createAssertionGroupWithoutCheckerInOrder(
        entryPointStepLogic, expected, reportingOptions, ::InOrderOnlyValuesAssertionCreator
    )

    override fun <E : Any, T : IterableLike> entriesInOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit
    ): Assertion = createAssertionGroupWithoutCheckerInOrder(
        entryPointStepLogic, assertionCreators, reportingOptions, ::InOrderOnlyEntriesAssertionCreator
    )


    override fun <E, T : IterableLike> valuesInOrderOnlyGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<E>>,
        inOrderOnlyReportingOptions: InOrderOnlyReportingOptions.() -> Unit,
        inAnyOrderOnlyReportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion = createAssertionGroupWithoutCheckerInOrderGrouped(
        entryPointStepLogic,
        groups,
        inOrderOnlyReportingOptions,
        inAnyOrderOnlyReportingOptions,
        ::InOrderOnlyGroupedValuesAssertionCreator
    )

    override fun <E : Any, T : IterableLike> entriesInOrderOnlyGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(Expect<E>.() -> Unit)?>>,
        inOrderOnlyReportingOptions: InOrderOnlyReportingOptions.() -> Unit,
        inAnyOrderOnlyReportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion = createAssertionGroupWithoutCheckerInOrderGrouped(
        entryPointStepLogic,
        groups,
        inOrderOnlyReportingOptions,
        inAnyOrderOnlyReportingOptions,
        ::InOrderOnlyGroupedEntriesAssertionCreator
    )


    private fun <E, T : IterableLike, SC, S : IterableLikeContains.SearchBehaviour> createAssertionGroupWithoutCheckerInAnyOrder(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>,
        expected: List<SC>,
        reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit,
        factory: ((T) -> Iterable<E>, S, InAnyOrderOnlyReportingOptions.() -> Unit) -> IterableLikeContains.Creator<T, SC>
    ): AssertionGroup {
        val creator = factory(entryPointStepLogic.converter, entryPointStepLogic.searchBehaviour, reportingOptions)
        return creator.createAssertionGroup(entryPointStepLogic.container, expected)
    }

    private fun <E, T : IterableLike, SC, S : IterableLikeContains.SearchBehaviour> createAssertionGroupWithoutCheckerInOrder(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>,
        expected: List<SC>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit,
        factory: ((T) -> Iterable<E>, S, InOrderOnlyReportingOptions.() -> Unit) -> IterableLikeContains.Creator<T, SC>
    ): AssertionGroup {
        val creator = factory(entryPointStepLogic.converter, entryPointStepLogic.searchBehaviour, reportingOptions)
        return creator.createAssertionGroup(entryPointStepLogic.container, expected)
    }

    private fun <E, T : IterableLike, SC, S : IterableLikeContains.SearchBehaviour> createAssertionGroupWithoutCheckerInOrderGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>,
        expected: List<SC>,
        inOrderOnlyReportingOptions: InOrderOnlyReportingOptions.() -> Unit,
        inAnyOrderOnlyReportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit,
        factory: ((T) -> Iterable<E>, S, InOrderOnlyReportingOptions.() -> Unit, InAnyOrderOnlyReportingOptions.() -> Unit) -> IterableLikeContains.Creator<T, SC>
    ): AssertionGroup {
        val creator = factory(
            entryPointStepLogic.converter,
            entryPointStepLogic.searchBehaviour,
            inOrderOnlyReportingOptions,
            inAnyOrderOnlyReportingOptions
        )
        return creator.createAssertionGroup(entryPointStepLogic.container, expected)
    }
}
