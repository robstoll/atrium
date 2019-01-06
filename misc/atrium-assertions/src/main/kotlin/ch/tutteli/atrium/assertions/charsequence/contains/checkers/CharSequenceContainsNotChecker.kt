@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Represents a check that an expected object is not contained in the search input.
 */
@Deprecated("Use CharSequenceContainsCheckers.newNotChecker; will be removed with 1.0.0", ReplaceWith("CharSequenceContainsCheckers.newNotChecker()", "ch.tutteli.atrium.creating.charsequence.contains.checkers.CharSequenceContainsCheckers"))
class CharSequenceContainsNotChecker : CharSequenceContains.Checker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = AssertImpl.builder.createDescriptive(DescriptionBasic.IS, 0) { foundNumberOfTimes == 0 }
}
