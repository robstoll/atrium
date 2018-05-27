package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Represents a check that an expected object is not contained in the search input.
 */
@Deprecated("Use IterableContainsCheckers.newNotChecker, will be removed with 1.0.0", ReplaceWith("IterableContainsCheckers.newNotChecker()", "ch.tutteli.atrium.creating.iterable.contains.checkers.IterableContainsCheckers"))
class IterableContainsNotChecker : IterableContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertImpl.builder.createDescriptive(DescriptionBasic.IS, 0, { foundNumberOfTimes == 0 })
}
