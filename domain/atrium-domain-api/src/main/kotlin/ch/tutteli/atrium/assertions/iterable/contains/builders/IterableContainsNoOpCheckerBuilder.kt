package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains

/**
 * Represents the *deprecated* "no [IterableContains.Checker]" option, meaning no checker shall be applied to a search result.
 *
 * The checking as such is then usually carried out be the [IterableContains.Creator].
 */
@Deprecated(
    "use the abstract class from package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContainsNoOpCheckerBuilder")
)
class IterableContainsNoOpCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    override val containsBuilder: IterableContainsBuilder<E, T, S>
) : IterableContainsCheckerBuilder<E, T, S> {

    override val checkers: List<IterableContains.Checker> = listOf(NotIntendedForUseChecker)

    private object NotIntendedForUseChecker : IterableContains.Checker {
        override fun createAssertion(foundNumberOfTimes: Int): Assertion = throw UnsupportedOperationException(
            "You used ${IterableContainsNoOpCheckerBuilder::class.java} but are still using its" +
                " ${IterableContainsNoOpCheckerBuilder<Int, Iterable<Int>, *>::checkers.name} which is a no go."
        )
    }
}
