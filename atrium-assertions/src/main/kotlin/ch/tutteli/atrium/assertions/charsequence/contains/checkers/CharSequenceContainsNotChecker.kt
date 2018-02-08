package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains

/**
 * Represents a check that an expected object is not contained in the search input.
 */
@Deprecated("use CharSequenceContainsCheckers.newNotChecker, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsCheckers.newNotChecker()", "ch.tutteli.atrium.creating.charsequence.contains.checkers.CharSequenceContainsCheckers"))
class CharSequenceContainsNotChecker : CharSequenceContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertionBuilder.descriptive.create(DescriptionBasic.IS, 0, { foundNumberOfTimes == 0 })
}
