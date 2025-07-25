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
    ): Assertion = toBeInRange(container, range) { subject ->
        listOf(
            createLowerBoundAssertion(subject, range.start),
            createUpperBoundAssertion(subject, range.endInclusive)
        )
    }

    private fun <T : Comparable<T>> createLowerBoundAssertion(subject: T, lower: T): Assertion =
        assertionBuilder.descriptive
            .withTest { subject >= lower }
            .withDescriptionAndRepresentation(
                DescriptionComparableExpectation.TO_BE_GREATER_THAN_OR_EQUAL_TO,
                lower
            )
            .build()

    private fun <T : Comparable<T>> createUpperBoundAssertion(subject: T, upper: T): Assertion =
        assertionBuilder.descriptive
            .withTest { subject <= upper }
            .withDescriptionAndRepresentation(
                DescriptionComparableExpectation.TO_BE_LESS_THAN_OR_EQUAL_TO,
                upper
            )
            .build()
}

internal fun <T : Comparable<T>> toBeInRange(
    container: AssertionContainer<out T>,
    range: ClosedRange<T>,
    explanatoryAssertionCreator: (T) -> List<Assertion>
): Assertion =
    assertionBuilder.descriptive
        .withTest(container.toExpect()) { it >= range.start && it <= range.endInclusive }
        .withHelpOnFailureBasedOnDefinedSubject(container.toExpect()) { subject ->
            assertionBuilder.explanatoryGroup
                .withDefaultType
                .withAssertions(explanatoryAssertionCreator(subject))
                .build()
        }
        .withDescriptionAndRepresentation(
            TranslatableWithArgs(
                DescriptionComparableExpectation.TO_BE_IN_RANGE,
                "${range.start}..${range.endInclusive}"
            ),
            range
        )
        .build()
