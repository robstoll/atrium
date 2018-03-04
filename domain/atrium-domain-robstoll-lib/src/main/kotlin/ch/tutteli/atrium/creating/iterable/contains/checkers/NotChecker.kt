package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Represents a check that an expected entry is not contained in the [Iterable].
 */
class NotChecker : IterableContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertionBuilder.descriptive.create(DescriptionBasic.IS, 0, { foundNumberOfTimes == 0 })
}
