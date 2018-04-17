package ch.tutteli.atrium.domain.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import java.util.*

/**
 * The access point to an implementation of [IterableContainsAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val iterableContainsAssertions by lazy { SingleServiceLoader.load(IterableContainsAssertions::class.java) }


/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableContainsAssertions {

    fun <E, T : Iterable<E>> valuesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion


    fun <E, T : Iterable<E>> valuesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion


    fun <E, T : Iterable<E>> valuesInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion
}
