package ch.tutteli.atrium.logic.creating.iterable.contains.steps

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Represents a "no [IterableLikeContains.Checker]" option, meaning no checker shall be applied to a search result.
 *
 * The checking as such is then usually carried out by the [IterableLikeContains.Creator].
 */
class NoOpCheckerStep<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>
) : IterableLikeContains.CheckerStepInternal<E, T, S> {

    override val checkers: List<IterableLikeContains.Checker> = listOf(NotIntendedForUseChecker)

    private object NotIntendedForUseChecker : IterableLikeContains.Checker {
        override fun createAssertion(foundNumberOfTimes: Int): Assertion = throw UnsupportedOperationException(
            "You have chosen to use ${NoOpCheckerStep::class.fullName} but are still using its checkers which is not allowed."
        )
    }
}
