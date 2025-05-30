//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.withHelpOnFailure
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.BigDecimalAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.*
import java.math.BigDecimal

class DefaultBigDecimalAssertions : BigDecimalAssertions {
    override fun <T : BigDecimal> isNumericallyEqualTo(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(IS_NUMERICALLY_EQUAL_TO, expected) {
            isNumericallyEqualTo(it, expected)
        }

    private fun <T : BigDecimal> isNumericallyEqualTo(subject: T, expected: T) =
        subject.compareTo(expected) == 0

    override fun <T : BigDecimal> isNotNumericallyEqualTo(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(IS_NOT_NUMERICALLY_EQUAL_TO, expected) {
            !isNumericallyEqualTo(it, expected)
        }

    override fun <T : BigDecimal> isEqualIncludingScale(
        container: AssertionContainer<T>,
        expected: T,
        nameOfIsNumericallyEqualTo: String
    ): Assertion =
        assertionBuilder.descriptive
            .withTest(container.toExpect()) { it == expected }
            .withHelpOnFailure {
                assertionBuilder.explanatoryGroup
                    .withHintType
                    .withExplanatoryAssertion(
                        FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL,
                        nameOfIsNumericallyEqualTo
                    )
                    .build()
            }
            .showBasedOnDefinedSubjectOnlyIf(container.toExpect()) {
                isNumericallyEqualTo(it, expected)
            }
            .withDescriptionAndRepresentation(IS_EQUAL_INCLUDING_SCALE, expected)
            .build()

    override fun <T : BigDecimal> isNotEqualIncludingScale(container: AssertionContainer<T>, expected: T): Assertion {
        // unfortunately we cannot give a hint about isNotNumericallyEqualTo, because <<10 is not 10.0>> holds
        // so we do not get to the point where we can detect that using this function instead of isNotNumericallyEqualTo
        // might not be the intention of the user
        return container.createDescriptiveAssertion(IS_NOT_EQUAL_INCLUDING_SCALE, expected) { it != expected }
    }

}
