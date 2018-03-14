package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.AnyAssertions
import ch.tutteli.atrium.robstoll.lib.creating.*

/**
 * Robstoll's implementation of [AnyAssertions].
 */
class AnyAssertionsImpl : AnyAssertions {

    override fun <T : Any> toBe(plant: AssertionPlant<T>, expected: T)
        = _toBe(plant, expected)

    override fun <T : Any> notToBe(plant: AssertionPlant<T>, expected: T)
        = _notToBe(plant, expected)

    override fun <T : Any> isSame(plant: AssertionPlant<T>, expected: T)
        = _isSame(plant, expected)

    override fun <T : Any> isNotSame(plant: AssertionPlant<T>, expected: T)
        = _isNotSame(plant, expected)

    override fun <T : Any?> isNull(plant: AssertionPlantNullable<T>)
        = _isNull(plant)
}
