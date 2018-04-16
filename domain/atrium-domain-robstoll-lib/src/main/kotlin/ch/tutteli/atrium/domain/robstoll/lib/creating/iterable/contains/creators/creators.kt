package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.NoOpCheckerOption

fun <E, T : Iterable<E>> _containsValuesInAnyOrder(
    checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion
    = createAssertionGroup(checkerOption, expected, otherExpected, ::InAnyOrderValuesAssertionCreator)

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


fun <E, T : Iterable<E>> _containsValuesInAnyOrderOnly(
    builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::InAnyOrderOnlyValuesAssertionCreator)
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


fun <E, T : Iterable<E>> _containsValuesInOrderOnly(
    builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checkerBuilder = NoOpCheckerOption(builder)
    return createAssertionGroupWithoutChecker(checkerBuilder, expected, otherExpected, ::InOrderOnlyValuesAssertionCreator)
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


private fun <E, T : Iterable<E>, SC, S : IterableContains.SearchBehaviour> createAssertionGroupWithoutChecker(
    checkerOption: IterableContains.CheckerOption<E, T, S>,
    expected: SC,
    otherExpected: Array<out SC>,
    factory: (S) -> IterableContains.Creator<T, SC>
): AssertionGroup {
    val creator = factory(checkerOption.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checkerOption.containsBuilder.plant, listOf(expected, *otherExpected))
}

private fun <E, T : Iterable<E>, SC, S : IterableContains.SearchBehaviour> createAssertionGroup(
    checkerOption: IterableContains.CheckerOption<E, T, S>,
    expected: SC,
    otherExpected: Array<out SC>,
    factory: (S, List<IterableContains.Checker>) -> IterableContains.Creator<T, SC>
): AssertionGroup {
    val creator = factory(checkerOption.containsBuilder.searchBehaviour, checkerOption.checkers)
    return creator.createAssertionGroup(checkerOption.containsBuilder.plant, listOf(expected, *otherExpected))
}
