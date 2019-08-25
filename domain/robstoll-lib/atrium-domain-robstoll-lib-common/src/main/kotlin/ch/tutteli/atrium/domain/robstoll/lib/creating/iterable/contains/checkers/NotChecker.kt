package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Represents a check that an expected entry is not contained in the [Iterable].
 */
class NotChecker : IterableContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        AssertImpl.builder.createDescriptive(DescriptionBasic.IS, 0) { foundNumberOfTimes == 0 }
}
