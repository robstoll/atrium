package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Represents a check that an expected object is not contained in the search input.
 */
class NotChecker : CharSequenceContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertImpl.builder.createDescriptive(DescriptionBasic.IS, 0, { foundNumberOfTimes == 0 })
}
