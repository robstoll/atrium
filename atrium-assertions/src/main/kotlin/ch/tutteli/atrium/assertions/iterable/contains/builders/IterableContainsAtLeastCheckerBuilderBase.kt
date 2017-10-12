package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsAtLeastChecker

abstract class IterableContainsAtLeastCheckerBuilderBase<E, T : Iterable<E>, D : IterableContainsAssertionCreator.IDecorator>(
    val times: Int,
    containsBuilder: IterableContainsBuilder<E, T, D>,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : IterableContainsCheckerBuilder<E, T, D>(containsBuilder) {

    override val checkers: List<IterableContainsAssertionCreator.IChecker> =
        listOf(IterableContainsAtLeastChecker(times, nameContainsNotFun, nameAtLeastFun))
}
