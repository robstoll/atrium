package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator
import ch.tutteli.atrium.creating.IAssertionPlant


abstract class IterableContainsCheckerBuilder<E, T : Iterable<E>, D : IterableContainsAssertionCreator.IDecorator>(
    val containsBuilder: IterableContainsBuilder<E, T, D>
) {

    abstract val checkers: List<IterableContainsAssertionCreator.IChecker>

    fun addAssertion(
        searcher: IterableContainsAssertionCreator.ISearcher<E, T, D>,
        expected: E,
        otherExpected: Array<out E>
    ): IAssertionPlant<T> {
        val assertionGroup = IterableContainsAssertionCreator(containsBuilder.decorator, searcher, checkers)
            .create(containsBuilder.plant, expected, *otherExpected)
        return containsBuilder.plant.addAssertion(assertionGroup)
    }
}
