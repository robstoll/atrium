package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [MapAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val mapEntryAssertions by lazy { loadSingleService(MapEntryAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Map],
 * which an implementation of the domain of Atrium has to provide.
 */
interface MapEntryAssertions {
    fun <K : Any, V : Any, T: Map.Entry<K, V>> isKeyValue(assertionContainer: Expect<T>, key: K, value: V): Assertion
    fun <K : Any, V : Any, T : Map.Entry<K?, V?>> isKeyValue(
        assertionContainer: Expect<T>,
        key: K?,
        value: V?,
        keyType: KClass<K>,
        valueType: KClass<V>
    ): Assertion

    fun <K, T : Map.Entry<K, *>> key(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, K>
    fun <V, T : Map.Entry<*, V>> value(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, V>


    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <K : Any, V : Any> isKeyValue(plant: AssertionPlant<Map.Entry<K, V>>, key: K, value: V): Assertion
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <K : Any> key(plant: AssertionPlant<Map.Entry<K, *>>, assertionCreator: AssertionPlant<K>.() -> Unit): Assertion
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <V : Any> value(
        plant: AssertionPlant<Map.Entry<*, V>>,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ): Assertion

    @Deprecated("Switch from Assert to Expect and use key; will be removed with 1.0.0")
    fun <K> nullableKey(
        plant: AssertionPlant<Map.Entry<K, *>>,
        assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ): Assertion

    @Deprecated("Switch from Assert to Expect and use value; will be removed with 1.0.0")
    fun <V> nullableValue(
        plant: AssertionPlant<Map.Entry<*, V>>,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ): Assertion
}
