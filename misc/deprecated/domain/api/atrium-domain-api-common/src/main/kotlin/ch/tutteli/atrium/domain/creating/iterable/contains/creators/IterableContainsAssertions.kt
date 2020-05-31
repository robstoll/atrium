@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.domain.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

/**
 * The access point to an implementation of [IterableContainsAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val iterableContainsAssertions by lazy { loadSingleService(IterableContainsAssertions::class) }


/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableContainsAssertions {

    fun <E, T : Iterable<E>> valuesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> entriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion


    fun <E, T : Iterable<E>> valuesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> entriesInAnyOrderOnly(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion


    fun <E, T : Iterable<E>> valuesInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> entriesInOrderOnly(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion


    fun <E, T : Iterable<E>> valuesInOrderOnlyGrouped(
        builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<E>>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyGrouped(
        builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(Expect<E>.() -> Unit)?>>
    ): Assertion


    @Deprecated("Switch from Assert to Expect and use entriesInAnyOrderWithAssert; will be removed with 1.0.0")
    fun <E : Any, T : Iterable<E?>> entriesInAnyOrderWithAssert(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    @Deprecated("Switch from Assert to Expect and use entriesInAnyOrderOnlyWithAssert; will be removed with 1.0.0")
    fun <E : Any, T : Iterable<E?>> entriesInAnyOrderOnlyWithAssert(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    @Deprecated("Switch from Assert to Expect and use entriesInOrderOnlyWithAssert; will be removed with 1.0.0")
    fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyWithAssert(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    @Deprecated("Switch from Assert to Expect and use entriesInOrderOnlyGroupedWithAssert; will be removed with 1.0.0")
    fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyGroupedWithAssert(
        builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(AssertionPlant<E>.() -> Unit)?>>
    ): Assertion
}
