package ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators.*

/**
 * Robstoll's implementation of [IterableContainsAssertions].
 */
class IterableContainsAssertionsImpl : IterableContainsAssertions {

    override fun <E, T : Iterable<E>> valuesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        expected: E,
        otherExpected: Array<out E>
    ): Assertion = _containsObjectsInAnyOrder(checkerOption, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        assertionCreator: AssertionPlant<E>.() -> Unit,
        otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
    ): Assertion = _containsEntriesInAnyOrder(checkerOption, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsNullableEntriesInAnyOrder(checkerOption, assertionCreator, otherAssertionCreators)

    override fun <E, T : Iterable<E>> valuesInAnyOrderOnly(
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

    override fun <E, T : Iterable<E>> valuesInOrderOnly(
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
