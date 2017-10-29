package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsAtLeastChecker

abstract class IterableContainsAtLeastCheckerBuilderBase<E, T : Iterable<E>, D : IIterableContains.IDecorator>(
    val times: Int,
    containsBuilder: IterableContainsBuilder<E, T, D>,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : IterableContainsCheckerBuilder<E, T, D>(containsBuilder) {

    override val checkers: List<IIterableContains.IChecker> =
        listOf(IterableContainsAtLeastChecker(times, nameContainsNotFun, nameAtLeastFun))
}
