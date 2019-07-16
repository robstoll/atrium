@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
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

@Deprecated("Use AssertImpl.iterable.contains.valuesInAnyOrder; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.valuesInAnyOrder(checker, expected, *otherExpected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion
    = createAssertionGroup(checker, expected, otherExpected, ::IterableContainsInAnyOrderObjectsAssertionCreator)

@Deprecated("Use AssertImpl.iterable.contains.entriesInAnyOrderWithAssert; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.entriesInAnyOrderWithAssert(checker, assertionCreator, *otherAssertionCreators)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion
    = createAssertionGroup(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

@Deprecated("Use AssertImpl.iterable.contains.nullableEntriesInAnyOrder; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.nullableEntriesInAnyOrder(checker, assertionCreator, *otherAssertionCreators)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
@JvmName("_containsNullableEntriesInAnyOrder")
fun <E : Any, T : Iterable<E?>> _containsEntriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion
    = createAssertionGroup(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

@Deprecated("Use AssertImpl.iterable.contains.valuesInAnyOrderOnly; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.valuesInAnyOrderOnly(builder, expected, *otherExpected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInAnyOrderOnlyObjectsAssertionCreator)
}

@Deprecated("Use AssertImpl.iterable.contains.entriesInAnyOrderOnlyWithAssert; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.entriesInAnyOrderOnlyWithAssert(builder, assertionCreator, *otherAssertionCreators)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

@Deprecated("Use AssertImpl.iterable.contains.nullableEntriesInAnyOrderOnly; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.nullableEntriesInAnyOrderOnly(builder, assertionCreator, *otherAssertionCreators)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
@JvmName("_containsNullableEntriesInAnyOrderOnly")
fun <E : Any, T : Iterable<E?>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

@Deprecated("Use AssertImpl.iterable.contains.valuesInOrderOnly; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.valuesInOrderOnly(builder, expected, *otherExpected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInOrderOnlyObjectsAssertionCreator)
}

@Deprecated("Use AssertImpl.iterable.contains.entriesInOrderOnlyWithAssert; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.entriesInOrderOnlyWithAssert(builder, assertionCreator, *otherAssertionCreators)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E : Any, T : Iterable<E>> _containsEntriesInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    assertionCreator: AssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>
): Assertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInOrderOnlyEntriesAssertionCreator)
}

@Deprecated("Use AssertImpl.iterable.contains.nullableEntriesInOrderOnly; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.nullableEntriesInOrderOnly(builder, assertionCreator, *otherAssertionCreators)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
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
    return creator.createAssertionGroup(checker.containsBuilder.subjectProvider, listOf(expected, *otherExpected))
}

private fun <E, T : Iterable<E>, S, B : IterableContains.SearchBehaviour> createAssertionGroup(
    checker: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B, List<ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Checker>) -> IterableContains.Creator<T, S>
): AssertionGroup {
    val creator = factory(checker.containsBuilder.searchBehaviour, checker.checkers)
    return creator.createAssertionGroup(checker.containsBuilder.subjectProvider, listOf(expected, *otherExpected))
}
