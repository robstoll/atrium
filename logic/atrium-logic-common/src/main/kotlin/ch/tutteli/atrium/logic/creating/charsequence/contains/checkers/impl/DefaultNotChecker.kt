package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.NotChecker
import ch.tutteli.atrium.translations.DescriptionBasic.IS

/**
 * Represents a check that an expected object is not contained in the search input.
 */
class DefaultNotChecker : NotChecker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        assertionBuilder.createDescriptive(IS, 0) { foundNumberOfTimes == 0 }
}
