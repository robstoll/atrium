package ch.tutteli.atrium.api.cc.en_UK.assertions.floatingpoint.iswitherrortolerance.builders

import ch.tutteli.atrium.assertions.floatingpoint.iswitherrortolerance.builders.ErrorToleranceBigDecimalBuilderBase
import ch.tutteli.atrium.assertions.floatingpoint.iswitherrortolerance.builders.ErrorToleranceDoubleBuilderBase
import ch.tutteli.atrium.assertions.floatingpoint.iswitherrortolerance.builders.ErrorToleranceFloatBuilderBase
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

class ErrorToleranceFloatBuilder(plant: AssertionPlant<Float>, tolerance: Float)
    : ErrorToleranceFloatBuilderBase(plant, tolerance, "tolerance")

class ErrorToleranceDoubleBuilder(plant: AssertionPlant<Double>, tolerance: Double)
    : ErrorToleranceDoubleBuilderBase(plant, tolerance, "tolerance")

class ErrorToleranceBigDecimalBuilder<out T : BigDecimal>(plant: AssertionPlant<T>, tolerance: T)
    : ErrorToleranceBigDecimalBuilderBase<T>(plant, tolerance, "tolerance")
