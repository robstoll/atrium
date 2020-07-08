//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MapEntryAssertions
import ch.tutteli.atrium.domain.creating.mapEntryAssertions
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [MapEntryAssertions].
 * In detail, it implements [MapEntryAssertions] by delegating to [mapEntryAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
object MapEntryAssertionsBuilder : MapEntryAssertions {
    override inline fun <K, V, T : Map.Entry<K, V>> isKeyValue(
        expect: Expect<T>,
        key: K,
        value: V
    ) = mapEntryAssertions.isKeyValue(expect, key, value)

    override inline fun <K, T : Map.Entry<K, *>> key(expect: Expect<T>) =
        mapEntryAssertions.key(expect)

    override inline fun <V, T : Map.Entry<*, V>> value(expect: Expect<T>) =
        mapEntryAssertions.value(expect)


    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <K : Any, V : Any> isKeyValue(
        plant: AssertionPlant<Map.Entry<K, V>>,
        key: K,
        value: V
    ): Assertion = mapEntryAssertions.isKeyValue(plant, key, value)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <K : Any> key(
        plant: AssertionPlant<Map.Entry<K, *>>,
        noinline assertionCreator: AssertionPlant<K>.() -> Unit
    ): Assertion = mapEntryAssertions.key(plant, assertionCreator)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <V : Any> value(
        plant: AssertionPlant<Map.Entry<*, V>>,
        noinline assertionCreator: AssertionPlant<V>.() -> Unit
    ): Assertion = mapEntryAssertions.value(plant, assertionCreator)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <K> nullableKey(
        plant: AssertionPlant<Map.Entry<K, *>>,
        noinline assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ): Assertion = mapEntryAssertions.nullableKey(plant, assertionCreator)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <V> nullableValue(
        plant: AssertionPlant<Map.Entry<*, V>>,
        noinline assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ): Assertion = mapEntryAssertions.nullableValue(plant, assertionCreator)
}
