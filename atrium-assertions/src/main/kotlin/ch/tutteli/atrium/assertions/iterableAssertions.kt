package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.creators.IterableContainsObjectsInAnyOrderAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
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

fun <E, T : Iterable<E>> _objects(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion {
    val creator = IterableContainsObjectsInAnyOrderAssertionCreator<E, T>(checker.containsBuilder.decorator, checker.checkers)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
