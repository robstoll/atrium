package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsNoOpCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant

@Deprecated("use IterableContainsAssertions.objectsInAnyOrder, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.objectsInAnyOrder(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion
    = createAssertionGroup(checker, expected, otherExpected, ::IterableContainsInAnyOrderObjectsAssertionCreator)

@Deprecated("use IterableContainsAssertions.entriesInAnyOrder, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.entriesInAnyOrder(checker, assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion
    = createAssertionGroup(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

@Deprecated("use IterableContainsAssertions.nullableEntriesInAnyOrder, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.nullableEntriesInAnyOrder(checker, assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
@JvmName("_containsNullableEntriesInAnyOrder")
fun <E : Any, T : Iterable<E?>> _containsEntriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion
    = createAssertionGroup(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

@Deprecated("use IterableContainsAssertions.objectsInAnyOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.objectsInAnyOrderOnly(builder, expected, *otherExpected)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInAnyOrderOnlyObjectsAssertionCreator)
}

@Deprecated("use IterableContainsAssertions.entriesInAnyOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.entriesInAnyOrderOnly(builder, assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

@Deprecated("use IterableContainsAssertions.nullableEntriesInAnyOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.nullableEntriesInAnyOrderOnly(builder, assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
@JvmName("_containsNullableEntriesInAnyOrderOnly")
fun <E : Any, T : Iterable<E?>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

@Deprecated("use IterableContainsAssertions.objectsInOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.objectsInOrderOnly(builder, expected, *otherExpected)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInOrderOnlyObjectsAssertionCreator)
}

@Deprecated("use IterableContainsAssertions.entriesInOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.entriesInOrderOnly(builder, assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
fun <E : Any, T : Iterable<E>> _containsEntriesInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInOrderOnlyEntriesAssertionCreator)
}

@Deprecated("use IterableContainsAssertions.nullableEntriesInOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsAssertions.nullableEntriesInOrderOnly(builder, assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions"))
@JvmName("_containsNullableEntriesInOrderOnly")
fun <E : Any, T : Iterable<E?>> _containsEntriesInOrderOnly(
    builder: IterableContainsBuilder<E?, T, IterableContainsInOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInOrderOnlyEntriesAssertionCreator)
}


private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroupWithoutChecker(
    checker: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checker.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}

private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroup(
    checker: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B, List<IterableContains.Checker>) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checker.containsBuilder.searchBehaviour, checker.checkers)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
