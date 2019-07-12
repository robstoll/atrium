package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.AnyAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotNull
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotNullBut
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNullIfNullGivenElse
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNullable
import kotlin.reflect.KClass

/**
 * Deprecated functionality which will be removed with 1.0.0
 */
abstract class AnyAssertionsDeprecatedImpl : AnyAssertions {

    override fun <T : Any> isNullable(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        expectedOrNull: T?
    ) = _isNullable(plant, type, expectedOrNull)

    override fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ) = _isNotNull(plant, type, assertionCreator)

    override fun <T : Any> isNotNullBut(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        expected: T
    ) = _isNotNullBut(plant, type, expected)

    override fun <T : Any> isNullIfNullGivenElse(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (AssertionPlant<T>.() -> Unit)?
    ) = _isNullIfNullGivenElse(plant, type, assertionCreatorOrNull)
}
