package ch.tutteli.atrium.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.NoOpCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checkerBuilder: IterableContains.CheckerBuilder<E, T, InAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion
    = createAssertionGroup(checkerBuilder, expected, otherExpected, ::InAnyOrderObjectsAssertionCreator)

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checkerBuilder: IterableContains.CheckerBuilder<E, T, InAnyOrderSearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion
    = createAssertionGroup(checkerBuilder, assertionCreator, otherAssertionCreators, ::InAnyOrderEntriesAssertionCreator)

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInAnyOrder(
    checkerBuilder: IterableContains.CheckerBuilder<E?, T, InAnyOrderSearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion
    = createAssertionGroup(checkerBuilder, assertionCreator, otherAssertionCreators, ::InAnyOrderEntriesAssertionCreator)

fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = NoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::InAnyOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checkerBuilder = NoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InAnyOrderOnlyEntriesAssertionCreator)
}

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInAnyOrderOnly(
    builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = NoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InAnyOrderOnlyEntriesAssertionCreator)
}

fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = NoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::InOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInOrderOnly(
    builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checkerBuilder = NoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InOrderOnlyEntriesAssertionCreator)
}

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInOrderOnly(
    builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = NoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InOrderOnlyEntriesAssertionCreator)
}


private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroupWithoutChecker(
    checkerBuilder: IterableContains.CheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checkerBuilder.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checkerBuilder.containsBuilder.plant, expected, otherExpected)
}

private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroup(
    checkerBuilder: IterableContains.CheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B, List<IterableContains.Checker>) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checkerBuilder.containsBuilder.searchBehaviour, checkerBuilder.checkers)
    return creator.createAssertionGroup(checkerBuilder.containsBuilder.plant, expected, otherExpected)
}
