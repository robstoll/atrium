package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertions
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.withFailureHint
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.*
import java.math.BigDecimal

fun <T : BigDecimal> _isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.createDescriptive(plant, IS_NUMERICALLY_EQUAL_TO, expected) { isNumericallyEqualTo(it, expected) }

private fun <T : BigDecimal> isNumericallyEqualTo(subject: T, expected: T)
     = subject.compareTo(expected) == 0

fun <T : BigDecimal> _isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.createDescriptive(plant, IS_NOT_NUMERICALLY_EQUAL_TO, expected) { !isNumericallyEqualTo(it, expected) }

fun <T : BigDecimal> _isEqualIncludingScale(
    plant: AssertionPlant<T>,
    expected: T,
    nameOfIsNumericallyEqualTo: String
): Assertion
    = AssertImpl.builder.descriptive
        .withTest(plant) { it == expected }
        .withFailureHint {
            AssertImpl.builder.explanatoryGroup
                .withDefaultType
                .withExplanatoryAssertions(
                    FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL,
                    nameOfIsNumericallyEqualTo
                )
                .build()
        }
        .showOnlyIfBasedOnSubject(plant) {
            ifDefined {
                isNumericallyEqualTo(it, expected)
            } ifAbsent { false }
        }
        .withDescriptionAndRepresentation(IS_EQUAL_INCLUDING_SCALE, expected)
        .build()

fun <T : BigDecimal> _isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
    // unfortunately we cannot give a hint about isNotNumericallyEqualTo, because <<10 is not 10.0>> holds
    // so we do not get to the point where we can detect that using this function instead of isNotNumericallyEqualTo
    // might not be the intention of the user
    = AssertImpl.builder.createDescriptive(plant, IS_NOT_EQUAL_INCLUDING_SCALE, expected) { it != expected }
