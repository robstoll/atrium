package ch.tutteli.atrium.robstoll.lib.creating.iterable.contains.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * Represents a "no [IterableContains.Checker]" option, meaning no checker shall be applied to a search result.
 *
 * The checking as such is then usually carried out by the [IterableContains.Creator].
 */
class NoOpCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    override val containsBuilder: IterableContains.Builder<E, T, S>
) : IterableContains.CheckerOption<E, T, S> {

    override val checkers: List<IterableContains.Checker> = listOf(NotIntendedForUseChecker)

    private object NotIntendedForUseChecker : IterableContains.Checker {
        override fun createAssertion(foundNumberOfTimes: Int): Assertion = throw UnsupportedOperationException(
            "You used ${NoOpCheckerOption::class.java} but are still using its" +
                " ${NoOpCheckerOption<Int, Iterable<Int>, *>::checkers.name} which is a no go."
        )
    }
}
