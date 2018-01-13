package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionBigDecimalAssertions.*
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import java.math.BigDecimal

fun <T : BigDecimal> _toBe(plant: AssertionPlant<T>, expected: T, nameOfIsNumericallyEqualTo: String): Assertion {
    val isEqual = try {
        plant.subject == expected
    } catch (e: PlantHasNoSubjectException) {
        //TODO hack, should not be necessary in this way (should be handled by an own assertion group type or such)
        true
    }
    return when {
        isEqual -> _toBe(plant, expected)
        isNumericallyEqualTo(plant, expected) -> failToBeWithHint(expected, nameOfIsNumericallyEqualTo)
        else -> _toBe(plant, expected)
    }
}

fun <T : BigDecimal> failToBeWithHint(expected: T, nameOfIsNumericallyEqualTo: String): Assertion {
    val explanatoryAssertion = listOf(AssertionGroup.Builder.explanatory.withDefault.create(
        BasicExplanatoryAssertion(RawString.create(TranslatableWithArgs(FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL, nameOfIsNumericallyEqualTo)))
    ))
    return FixHoldsAssertionGroup(DefaultListAssertionGroupType, DescriptionAnyAssertion.TO_BE, expected, explanatoryAssertion, false)
}

fun <T : BigDecimal> _isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = BasicDescriptiveAssertion(IS_NUMERICALLY_EQUAL_TO, expected, { isNumericallyEqualTo(plant, expected) })

private fun <T : BigDecimal> isNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = plant.subject.compareTo(expected) == 0

fun <T : BigDecimal> _isNotNumericallyEqualTo(plant: AssertionPlant<T>, expected: T)
    = BasicDescriptiveAssertion(IS_NOT_NUMERICALLY_EQUAL_TO, expected, { isNumericallyEqualTo(plant, expected).not() })
