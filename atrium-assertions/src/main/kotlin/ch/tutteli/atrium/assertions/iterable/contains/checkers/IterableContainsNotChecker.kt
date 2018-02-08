package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionBuilder
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains

/**
 * Represents a check that an expected object is not contained in the search input.
 */
@Deprecated("use IterableContainsCheckers.newNotChecker, will be removed with 1.0.0", ReplaceWith("IterableContainsCheckers.newNotChecker()", "ch.tutteli.atrium.creating.iterable.contains.checkers.IterableContainsCheckers"))
class IterableContainsNotChecker : IterableContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertionBuilder.descriptive.create(DescriptionBasic.IS, 0, { foundNumberOfTimes == 0 })
}
