package ch.tutteli.atrium.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour

/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IIterableContainsAssertions {
    fun <E, T : Iterable<E>> objectsInAnyOrder(
        checkerBuilder: IterableContains.CheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInAnyOrder(
        checkerBuilder: IterableContains.CheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(
        checkerBuilder: IterableContains.CheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    fun <E, T : Iterable<E>> objectsInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(
        builder: IterableContains.Builder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    fun <E, T : Iterable<E>> objectsInOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(
        builder: IterableContains.Builder<E?, T, IterableContainsInOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion
}
