package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsNoOpCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.creators.*
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

fun <E, T : Iterable<E>> _containsBuilder(plant: IAssertionPlant<T>)
    = IterableContainsBuilder(plant, IterableContainsNoOpDecorator)

fun <E, T : Iterable<E>> _containsNot(plant: IAssertionPlant<T>, expected: E, vararg otherExpected: E): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    listOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            BasicAssertion(DescriptionIterableAssertion.CONTAINS_NOT, it ?: RawString.NULL, { !plant.subject.contains(it) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion
    = createAssertionGroup(checker, expected, otherExpected, ::IterableContainsInAnyOrderObjectsAssertionCreator)

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>,
    assertionCreator: IAssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out IAssertionPlant<E>.() -> Unit>
): IAssertion
    = createAssertionGroup(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlyDecorator>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInAnyOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlyDecorator>,
    assertionCreator: IAssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out IAssertionPlant<E>.() -> Unit>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlyDecorator>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInOrderOnlyObjectsAssertionCreator)
}

private fun <E, T : Iterable<E>, P, D : IIterableContains.IDecorator> createAssertionGroupWithoutChecker(
    checker: IterableContainsCheckerBuilder<E, T, D>,
    expected: P,
    otherExpected: Array<out P>,
    factory: (D) -> IIterableContains.ICreator<T, P>
): IAssertionGroup {
    val creator = factory(checker.containsBuilder.decorator)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}


private fun <E, T : Iterable<E>, P, D : IIterableContains.IDecorator> createAssertionGroup(
    checker: IterableContainsCheckerBuilder<E, T, D>,
    expected: P,
    otherExpected: Array<out P>,
    factory: (D, List<IIterableContains.IChecker>) -> IIterableContains.ICreator<T, P>
): IAssertionGroup {
    val creator = factory(checker.containsBuilder.decorator, checker.checkers)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
