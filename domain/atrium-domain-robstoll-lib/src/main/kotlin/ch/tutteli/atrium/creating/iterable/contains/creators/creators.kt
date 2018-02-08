package ch.tutteli.atrium.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsNoOpCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour

fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checkerBuilder: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion
    = createAssertionGroup(checkerBuilder, expected, otherExpected, ::IterableContainsInAnyOrderObjectsAssertionCreator)

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checkerBuilder: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion
    = createAssertionGroup(checkerBuilder, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInAnyOrder(
    checkerBuilder: IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion
    = createAssertionGroup(checkerBuilder, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::IterableContainsInAnyOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checkerBuilder = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::IterableContainsInOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checkerBuilder = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::IterableContainsInOrderOnlyEntriesAssertionCreator)
}

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInOrderOnly(
    builder: IterableContainsBuilder<E?, T, IterableContainsInOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::IterableContainsInOrderOnlyEntriesAssertionCreator)
}


private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroupWithoutChecker(
    checkerBuilder: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checkerBuilder.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checkerBuilder.containsBuilder.plant, expected, otherExpected)
}

private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroup(
    checkerBuilder: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B, List<IterableContains.Checker>) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checkerBuilder.containsBuilder.searchBehaviour, checkerBuilder.checkers)
    return creator.createAssertionGroup(checkerBuilder.containsBuilder.plant, expected, otherExpected)
}
