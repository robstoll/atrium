package ch.tutteli.atrium.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

/**
 * Robstoll's implementation of [IIterableContainsAssertions].
 */
object IterableContainsAssertions : IIterableContainsAssertions {

    override fun <E, T : Iterable<E>> objectsInAnyOrder(
        checkerBuilder: IterableContains.CheckerBuilder<E, T, InAnyOrderSearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion = _containsObjectsInAnyOrder(checkerBuilder, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInAnyOrder(
        checkerBuilder: IterableContains.CheckerBuilder<E, T, InAnyOrderSearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion = _containsEntriesInAnyOrder(checkerBuilder, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(
        checkerBuilder: IterableContains.CheckerBuilder<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsNullableEntriesInAnyOrder(checkerBuilder, assertionCreator, otherAssertionCreators)

    override fun <E, T : Iterable<E>> objectsInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion = _containsObjectsInAnyOrderOnly(builder, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion = _containsEntriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsNullableEntriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override fun <E, T : Iterable<E>> objectsInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion = _containsObjectsInOrderOnly(builder, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion = _containsEntriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsNullableEntriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)
}
