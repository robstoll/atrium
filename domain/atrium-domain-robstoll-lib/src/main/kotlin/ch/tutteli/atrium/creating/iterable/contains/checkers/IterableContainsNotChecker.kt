package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Represents a check that an expected entry is not contained in the [Iterable].
 */
class IterableContainsNotChecker : IterableContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertionBuilder.descriptive.create(DescriptionBasic.IS, 0, { foundNumberOfTimes == 0 })
}
