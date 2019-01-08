@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.creating.MapEntryAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import ch.tutteli.atrium.domain.creating.mapAssertions
import ch.tutteli.atrium.domain.creating.mapEntryAssertions
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [MapEntryAssertions].
 * In detail, it implements [MapEntryAssertions] by delegating to [mapEntryAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object MapEntryAssertionsBuilder : MapEntryAssertions {
    override inline fun <K : Any> key(
        plant: AssertionPlant<Map.Entry<K, *>>,
        noinline assertionCreator: AssertionPlant<K>.() -> Unit
    ): Assertion = mapEntryAssertions.key(plant, assertionCreator)

    override inline fun <V : Any> value(
        plant: AssertionPlant<Map.Entry<*, V>>,
        noinline assertionCreator: AssertionPlant<V>.() -> Unit
    ): Assertion = mapEntryAssertions.value(plant, assertionCreator)

    override inline fun <K> nullableKey(
        plant: AssertionPlant<Map.Entry<K, *>>,
        noinline assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ): Assertion = mapEntryAssertions.nullableKey(plant, assertionCreator)

    override inline fun <V> nullableValue(
        plant: AssertionPlant<Map.Entry<*, V>>,
        noinline assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ): Assertion = mapEntryAssertions.nullableValue(plant, assertionCreator)
}
