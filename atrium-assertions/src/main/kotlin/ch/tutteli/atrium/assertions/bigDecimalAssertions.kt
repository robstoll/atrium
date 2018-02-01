package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionBigDecimalAssertion.*
import ch.tutteli.atrium.assertions.assertionbuilder.fixHoldsGroup
import ch.tutteli.atrium.assertions.assertionbuilder.withFailureHint
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import java.math.BigDecimal

fun <T : BigDecimal> _isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_NUMERICALLY_EQUAL_TO, expected, { isNumericallyEqualTo(plant, expected) })

private fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = plant.subject.compareTo(expected) == 0

fun <T : BigDecimal> _isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_NOT_NUMERICALLY_EQUAL_TO, expected, { !isNumericallyEqualTo(plant, expected) })

fun <T : BigDecimal> _isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion
    = AssertionBuilder.descriptive
        .withFailureHint {
            AssertionBuilder.explanatoryGroup.withDefault.createWithExplanatoryAssertion(
                FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL, nameOfIsNumericallyEqualTo)
        }
        .showOnlyIf { isNumericallyEqualTo(plant, expected) }
        .create(IS_EQUAL_INCLUDING_SCALE, expected, { plant.subject == expected })

fun <T : BigDecimal> _isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
    // unfortunately we cannot give a hint about isNotNumericallyEqualTo, because <<10 is not 10.0>> holds
    // so we do not get to the point where we can detect that it might not be the intention of the user
    = AssertionBuilder.descriptive.create(IS_NOT_EQUAL_INCLUDING_SCALE, expected, { plant.subject != expected })
