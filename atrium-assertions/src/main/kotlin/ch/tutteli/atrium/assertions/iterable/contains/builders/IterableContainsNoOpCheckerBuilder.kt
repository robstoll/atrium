package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains

class IterableContainsNoOpCheckerBuilder<E, T : Iterable<E>, D : IIterableContains.IDecorator>(
    containsBuilder: IterableContainsBuilder<E, T, D>
) : IterableContainsCheckerBuilder<E, T, D>(containsBuilder) {

    override val checkers: List<IIterableContains.IChecker> = listOf(NotIntendedForUseChecker)

    object NotIntendedForUseChecker : IIterableContains.IChecker {
        override fun createAssertion(foundNumberOfTimes: Int): IAssertion = throw UnsupportedOperationException(
            "You used ${IterableContainsNoOpCheckerBuilder::class.java} but are still using its" +
                " ${IterableContainsNoOpCheckerBuilder<Int, Iterable<Int>, *>::checkers.name} which is a no go."
        )
    }
}
