package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionBigDecimalAssertion.*
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import java.math.BigDecimal

fun <T : BigDecimal> _isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_NUMERICALLY_EQUAL_TO, expected, { isNumericallyEqualTo(plant, expected) })

private fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = plant.subject.compareTo(expected) == 0

fun <T : BigDecimal> _isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_NOT_NUMERICALLY_EQUAL_TO, expected, { isNumericallyEqualTo(plant, expected).not() })

fun <T : BigDecimal> _isEqualIncludingScale(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion {
    val isEqual = try {
        plant.subject == expected
    } catch (e: PlantHasNoSubjectException) {
        //TODO hack, should not be necessary in this way (should be handled by an own assertion group type or such)
        true
    }
    return when {
        isEqual -> isEqualIncludingScale(plant, expected)
        isNumericallyEqualTo(plant, expected) -> failToBeWithHint(expected, nameOfIsNumericallyEqualTo)
        else -> isEqualIncludingScale(plant, expected)
    }
}

private fun <T : BigDecimal> isEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_EQUAL_INCLUDING_SCALE, expected, { plant.subject == expected })

fun <T : BigDecimal> failToBeWithHint(expected: T, nameOfIsNumericallyEqualTo: String): Assertion {
    val explanatoryAssertion = AssertionBuilder.explanatoryGroup.withDefault.createWithExplanatoryAssertion(
        FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL, nameOfIsNumericallyEqualTo
    )
    return FixHoldsAssertionGroup(DefaultListAssertionGroupType, IS_EQUAL_INCLUDING_SCALE, expected, listOf(explanatoryAssertion), false)
}

fun <T : BigDecimal> _isNotEqualIncludingScale(plant: AssertionPlant<T>, expected: T)
    // unfortunately we cannot give a hint about isNotNumericallyEqualTo, because <<10 is not 10.0>> holds
    // so we do not get to the point where we can detect that it might not be the intention of the user
    = AssertionBuilder.descriptive.create(IS_NOT_EQUAL_INCLUDING_SCALE, expected, { plant.subject != expected })
