package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains

/**
 * Represents a "no [IterableContains.Checker]" option, meaning no checker shall be applied to a search result.
 *
 * The checking as such is then usually carried out be the [IterableContains.Creator].
 */
class IterableContainsNoOpCheckerBuilder<E, T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    containsBuilder: IterableContainsBuilder<E, T, S>
) : IterableContainsCheckerBuilder<E, T, S>(containsBuilder) {

    override val checkers: List<IterableContains.Checker> = listOf(NotIntendedForUseChecker)

    private object NotIntendedForUseChecker : IterableContains.Checker {
        override fun createAssertion(foundNumberOfTimes: Int): Assertion = throw UnsupportedOperationException(
            "You used ${IterableContainsNoOpCheckerBuilder::class.java} but are still using its" +
                " ${IterableContainsNoOpCheckerBuilder<Int, Iterable<Int>, *>::checkers.name} which is a no go."
        )
    }
}
