//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain logic of Atrium has to provide.
 */
interface IterableLikeContainsAssertions {

    fun <E, T : IterableLike> valuesInAnyOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>,
        reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion

    fun <E : Any, T : IterableLike> entriesInAnyOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>,
        reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion


    fun <E, T : IterableLike> valuesInOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit
    ): Assertion

    fun <E : Any, T : IterableLike> entriesInOrderOnly(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit
    ): Assertion


    fun <E, T : IterableLike> valuesInOrderOnlyGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<E>>,
        inOrderOnlyReportingOptions: InOrderOnlyReportingOptions.() -> Unit,
        inAnyOrderOnlyReportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion

    fun <E : Any, T : IterableLike> entriesInOrderOnlyGrouped(
        entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(Expect<E>.() -> Unit)?>>,
        inOrderOnlyReportingOptions: InOrderOnlyReportingOptions.() -> Unit,
        inAnyOrderOnlyReportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
    ): Assertion
}
