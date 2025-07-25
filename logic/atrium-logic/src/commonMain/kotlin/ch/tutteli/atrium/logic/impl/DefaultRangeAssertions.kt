package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withHelpOnFailureBasedOnDefinedSubject
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.RangeAssertions
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.*

class DefaultRangeAssertions : RangeAssertions {

    override fun <T : Comparable<T>> toBeInRange(
        container: AssertionContainer<T>,
        range: ClosedRange<T>
    ): Assertion = toBeInRange(
        container,
        range.start,
        range.endInclusive
    ) { subject ->
        listOf(
            createLowerBoundAssertion(subject, range.start),
            createUpperBoundAssertion(subject, range.endInclusive)
        )
    }

    private fun <T : Comparable<T>> toBeInRange(
        container: AssertionContainer<out T>,
        lower: T,
        upper: T,
        explanatoryAssertionCreator: (T) -> List<Assertion>
    ): Assertion =
        assertionBuilder.descriptive
            .withTest(container.toExpect()) { it >= lower && it <= upper }
            .withHelpOnFailureBasedOnDefinedSubject(container.toExpect()) { subject ->
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(explanatoryAssertionCreator(subject))
                    .build()
            }
            .withDescriptionAndRepresentation(
                TranslatableWithArgs(TO_BE_IN_RANGE, "$lower..$upper"),
                "$lower..$upper"
            )
            .build()

    private fun <T : Comparable<T>> createLowerBoundAssertion(
        subject: T,
        lower: T
    ): Assertion = assertionBuilder.descriptive
        .withTest { subject >= lower }
        .withDescriptionAndRepresentation(
            TO_BE_GREATER_THAN_OR_EQUAL_TO,
            lower
        )
        .build()

    private fun <T : Comparable<T>> createUpperBoundAssertion(
        subject: T,
        upper: T
    ): Assertion = assertionBuilder.descriptive
        .withTest { subject <= upper }
        .withDescriptionAndRepresentation(
            TO_BE_LESS_THAN_OR_EQUAL_TO,
            upper
        )
        .build()
}
