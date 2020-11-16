package ch.tutteli.atrium.logic.creating.iterablelike.contains.checkers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.creating.iterablelike.contains.checkers.NotChecker
import ch.tutteli.atrium.translations.DescriptionBasic

/**
 * Represents a check that an expected entry is not contained in the [Iterable].
 */
class DefaultNotChecker : NotChecker {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        assertionBuilder.createDescriptive(DescriptionBasic.IS, 0) { foundNumberOfTimes == 0 }
}
