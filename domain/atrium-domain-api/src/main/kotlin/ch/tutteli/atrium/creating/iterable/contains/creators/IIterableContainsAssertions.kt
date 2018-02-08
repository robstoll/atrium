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
    fun <E, T : Iterable<E>> containsObjectsInAnyOrder(
        checkerBuilder: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> containsEntriesInAnyOrder(
        checkerBuilder: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> containsNullableEntriesInAnyOrder(
        checkerBuilder: IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    fun <E, T : Iterable<E>> containsObjectsInAnyOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> containsEntriesInAnyOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> containsNullableEntriesInAnyOrderOnly(
        builder: IterableContainsBuilder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion

    fun <E, T : Iterable<E>> containsObjectsInOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion

    fun <E : Any, T : Iterable<E>> containsEntriesInOrderOnly(
        builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion

    fun <E : Any, T : Iterable<E?>> containsNullableEntriesInOrderOnly(
        builder: IterableContainsBuilder<E?, T, IterableContainsInOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion
}
