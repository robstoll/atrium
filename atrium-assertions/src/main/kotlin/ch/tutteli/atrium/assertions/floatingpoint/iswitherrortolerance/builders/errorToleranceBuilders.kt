package ch.tutteli.atrium.assertions.floatingpoint.iswitherrortolerance.builders

import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

abstract class ErrorToleranceBuilderBase<out T : Number>(
    val plant: AssertionPlant<T>,
    val tolerance: T,
    paramName: String,
    isGreaterThanZero: Boolean
) {
    init {
        require(isGreaterThanZero) {
            "$paramName needs to be bigger than 0"
        }
    }
}

abstract class ErrorToleranceFloatBuilderBase(plant: AssertionPlant<Float>, tolerance: Float, paramName: String)
    : ErrorToleranceBuilderBase<Float>(plant, tolerance, paramName, tolerance > 0.0f)

abstract class ErrorToleranceDoubleBuilderBase(plant: AssertionPlant<Double>, tolerance: Double, paramName: String)
    : ErrorToleranceBuilderBase<Double>(plant, tolerance, paramName, tolerance > 0.0)

abstract class ErrorToleranceBigDecimalBuilderBase<out T : BigDecimal>(plant: AssertionPlant<T>, tolerance: T, paramName: String)
    : ErrorToleranceBuilderBase<T>(plant, tolerance, paramName, tolerance > BigDecimal.ZERO)
