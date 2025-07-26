package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withHelpOnFailureBasedOnDefinedSubject
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.RangeAssertions
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

class DefaultRangeAssertions : RangeAssertions {

    override fun <T : Comparable<T>> toBeInRange(
        container: AssertionContainer<T>,
        range: ClosedRange<T>
    ): Assertion =
        assertionBuilder.descriptive
            .withTest(container.toExpect()) { it >= range.start && it <= range.endInclusive }
            .withDescriptionAndRepresentation(
                DescriptionComparableExpectation.TO_BE_IN_RANGE,
                range,
            )
            .build()
}
