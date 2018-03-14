package ch.tutteli.atrium.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.robstoll.lib.creating.iterable.contains.builders.NoOpCheckerOption

fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion
    = createAssertionGroup(checkerOption, expected, otherExpected, ::InAnyOrderObjectsAssertionCreator)

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion
    = createAssertionGroup(checkerOption, assertionCreator, otherAssertionCreators, ::InAnyOrderEntriesAssertionCreator)

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInAnyOrder(
    checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion
    = createAssertionGroup(checkerOption, assertionCreator, otherAssertionCreators, ::InAnyOrderEntriesAssertionCreator)

fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::InAnyOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InAnyOrderOnlyEntriesAssertionCreator)
}

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInAnyOrderOnly(
    builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InAnyOrderOnlyEntriesAssertionCreator)
}

fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::InOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInOrderOnly(
    builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InOrderOnlyEntriesAssertionCreator)
}

fun <E : Any, T : Iterable<E?>> _containsNullableEntriesInOrderOnly(
    builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, assertionCreator, otherAssertionCreators, ::InOrderOnlyEntriesAssertionCreator)
}


private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroupWithoutChecker(
    checkerOption: IterableContains.CheckerOption<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checkerOption.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checkerOption.containsBuilder.plant, expected, otherExpected)
}

private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroup(
    checkerOption: IterableContains.CheckerOption<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B, List<IterableContains.Checker>) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checkerOption.containsBuilder.searchBehaviour, checkerOption.checkers)
    return creator.createAssertionGroup(checkerOption.containsBuilder.plant, expected, otherExpected)
}
