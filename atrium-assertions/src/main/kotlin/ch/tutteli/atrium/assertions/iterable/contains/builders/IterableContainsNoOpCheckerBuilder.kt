package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains

/**
 * Represents a "no [IIterableContains.IChecker]" option, meaning no checker shall be applied to a search result.
 *
 * The checking as such is then usually carried out be the [IIterableContains.ICreator].
 */
class IterableContainsNoOpCheckerBuilder<E, T : Iterable<E>, S : IIterableContains.ISearchBehaviour>(
    containsBuilder: IterableContainsBuilder<E, T, S>
) : IterableContainsCheckerBuilder<E, T, S>(containsBuilder) {

    override val checkers: List<IIterableContains.IChecker> = listOf(NotIntendedForUseChecker)

    private object NotIntendedForUseChecker : IIterableContains.IChecker {
        override fun createAssertion(foundNumberOfTimes: Int): IAssertion = throw UnsupportedOperationException(
            "You used ${IterableContainsNoOpCheckerBuilder::class.java} but are still using its" +
                " ${IterableContainsNoOpCheckerBuilder<Int, Iterable<Int>, *>::checkers.name} which is a no go."
        )
    }
}
