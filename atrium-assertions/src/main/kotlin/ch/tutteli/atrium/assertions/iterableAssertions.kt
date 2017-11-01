package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.creators.IterableContainsEntriesInAnyOrderAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.creators.IterableContainsObjectsInAnyOrderAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.creators.IterableContainsObjectsInAnyOrderOnlyAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

fun <E, T : Iterable<E>> _containsNot(plant: IAssertionPlant<T>, expected: E, vararg otherExpected: E): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    listOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            BasicAssertion(DescriptionIterableAssertion.CONTAINS_NOT, it ?: RawString.NULL, { !plant.subject.contains(it) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <E, T : Iterable<E>> _objectsInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion
    = createAssertionGroup(checker, expected, otherExpected, ::IterableContainsObjectsInAnyOrderAssertionCreator)

fun <E : Any, T : Iterable<E>> _entriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>,
    createAssertions: IAssertionPlant<E>.() -> Unit,
    otherCreateAssertionsFun: Array<out IAssertionPlant<E>.() -> Unit>
): IAssertion
    = createAssertionGroup(checker, createAssertions, otherCreateAssertionsFun, ::IterableContainsEntriesInAnyOrderAssertionCreator)

fun <E, T : Iterable<E>> _objectsInAnyOrderOnly(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderOnlyDecorator>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion
    = createAssertionGroup(checker, expected, otherExpected, ::IterableContainsObjectsInAnyOrderOnlyAssertionCreator)


private fun <E, T : Iterable<E>, P, D: IIterableContains.IDecorator> createAssertionGroup(
    checker: IterableContainsCheckerBuilder<E, T, D>,
    expected: P,
    otherExpected: Array<out P>,
    factory: (D, List<IIterableContains.IChecker>) -> IIterableContains.ICreator<T, P>
): IAssertionGroup {
    val creator = factory(checker.containsBuilder.decorator, checker.checkers)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
