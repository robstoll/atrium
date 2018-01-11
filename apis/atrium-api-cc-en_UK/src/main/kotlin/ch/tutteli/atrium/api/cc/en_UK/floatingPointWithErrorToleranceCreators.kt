package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.floatingpoint.iswitherrortolerance.builders.ErrorToleranceBigDecimalBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.floatingpoint.iswitherrortolerance.builders.ErrorToleranceDoubleBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.floatingpoint.iswitherrortolerance.builders.ErrorToleranceFloatBuilder
import ch.tutteli.atrium.assertions._toBeWithErrorTolerance
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

fun ErrorToleranceFloatBuilder.equalTo(expected: Float): AssertionPlant<Float>
    = plant.addAssertion(_toBeWithErrorTolerance(plant, expected, tolerance))

fun ErrorToleranceDoubleBuilder.equalTo(expected: Double): AssertionPlant<Double>
    = plant.addAssertion(_toBeWithErrorTolerance(plant, expected, tolerance))

fun <T : BigDecimal> ErrorToleranceBigDecimalBuilder<T>.equalTo(expected: BigDecimal): AssertionPlant<T>
    = plant.addAssertion(_toBeWithErrorTolerance(plant, expected, tolerance))

