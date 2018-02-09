package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.withFailureHint
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.*
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.math.absoluteValue

fun _toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
    = toBeWithErrorToleranceOfFloatOrDouble(plant, expected, tolerance, { (plant.subject - expected).absoluteValue })

fun _toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
    = toBeWithErrorToleranceOfFloatOrDouble(plant, expected, tolerance, { (plant.subject - expected).absoluteValue })

fun <T : BigDecimal> _toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion {
    val absDiff = { (plant.subject - expected).abs() }
    return toBeWithErrorTolerance(expected, tolerance, absDiff) { df ->
        listOf(
            createToBeWithErrorToleranceExplained(df, plant, expected, absDiff, tolerance)
        )
    }
}

private fun <T : Comparable<T>> toBeWithErrorToleranceOfFloatOrDouble(plant: AssertionPlant<T>, expected: T, tolerance: T, absDiff: () -> T): Assertion {
    return toBeWithErrorTolerance(expected, tolerance, absDiff) { df ->
        listOf(
            AssertionBuilder.explanatory.create(
                FAILURE_DUE_TO_FLOATING_POINT_NUMBER,
                plant.subject::class.java.name
            ),
            createToBeWithErrorToleranceExplained(df, plant, expected, absDiff, tolerance)
        )
    }
}

private fun <T : Comparable<T>> createToBeWithErrorToleranceExplained(df: DecimalFormat, plant: AssertionPlant<T>, expected: T, absDiff: () -> T, tolerance: T)
    = AssertionBuilder.explanatory.create(TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED, df.format(plant.subject), df.format(expected), df.format(absDiff()), df.format(tolerance))

private fun <T : Comparable<T>> toBeWithErrorTolerance(expected: T, tolerance: T, absDiff: () -> T, explanatoryAssertionCreator: (DecimalFormat) -> List<Assertion>): Assertion
    = AssertionBuilder.descriptive
        .withFailureHint {
            //TODO that's not nice in case we use it in an Iterable contains assertion, for instance contains...entry { toBeWithErrorTolerance(x, 0.01) }
            //we do not want to see the failure nor the exact check in the 'an entry which...' part
            //same problematic applies to feature assertions within an identification lambda
            val df = DecimalFormat("###,##0.0")
            df.maximumFractionDigits = 340
            AssertionBuilder.explanatoryGroup.withDefault.create(
                explanatoryAssertionCreator(df)
            )
        }
        .showForAnyFailure
        .create(TranslatableWithArgs(TO_BE_WITH_ERROR_TOLERANCE, tolerance), expected, { absDiff() <= tolerance })
