//TODO 1.1.0 rename package to iterablelike?
package ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.NotChecker
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL

/**
 * Represents a check that an expected entry is not contained in the [Iterable].
 */
class DefaultNotChecker : NotChecker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        assertionBuilder.createDescriptive(TO_EQUAL, 0) { foundNumberOfTimes == 0 }
}
