package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AnyAssertions
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

@Deprecated("use AnyAssertions.toBe instead, will be removed with 1.0.0", ReplaceWith("AnyAssertions.toBe(plant, expected)"))
fun <T : Any> _toBe(plant: AssertionPlant<T>, expected: T): Assertion
    = AnyAssertions.toBe(plant, expected)

@Deprecated("use AnyAssertions.notToBe instead, will be removed with 1.0.0", ReplaceWith("AnyAssertions.notToBe(plant, expected)"))
fun <T : Any> _notToBe(plant: AssertionPlant<T>, expected: T): Assertion
    = AnyAssertions.notToBe(plant, expected)

@Deprecated("use AnyAssertions.isSame instead, will be removed with 1.0.0", ReplaceWith("AnyAssertions.isSame(plant, expected)"))
fun <T : Any> _isSame(plant: AssertionPlant<T>, expected: T): Assertion
    = AnyAssertions.isSame(plant, expected)

@Deprecated("use AnyAssertions.isNotSame instead, will be removed with 1.0.0", ReplaceWith("AnyAssertions.isNotSame(plant, expected)"))
fun <T : Any> _isNotSame(plant: AssertionPlant<T>, expected: T): Assertion
    = AnyAssertions.isNotSame(plant, expected)

@Deprecated("use AnyAssertions.isNull instead, will be removed with 1.0.0", ReplaceWith("AnyAssertions.isNull(plant)"))
fun <T : Any?> _isNull(plant: AssertionPlantNullable<T>): Assertion
    = AnyAssertions.isNull(plant)
