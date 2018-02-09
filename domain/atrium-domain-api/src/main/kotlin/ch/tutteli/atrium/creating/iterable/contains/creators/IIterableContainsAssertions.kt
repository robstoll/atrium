package ch.tutteli.atrium.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour

/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IIterableContainsAssertions {
    fun <E, T : Iterable<E>> objectsInAnyOrder(
        checkerBuilder: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInAnyOrder(
        checkerBuilder: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(
        checkerBuilder: IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    fun <E, T : Iterable<E>> objectsInAnyOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(
        builder: IterableContainsBuilder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    fun <E, T : Iterable<E>> objectsInOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> entriesInOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(
        builder: IterableContainsBuilder<E?, T, IterableContainsInOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion
}
