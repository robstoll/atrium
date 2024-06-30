//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.NotChecker
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL

/**
 * Represents a check that an expected search criterion is not contained in the search input.
 */
class DefaultNotChecker : NotChecker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        assertionBuilder.createDescriptive(TO_EQUAL, 0) { foundNumberOfTimes == 0 }
}
