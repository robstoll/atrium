package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

/**
 * The access point to an implementation of [PairAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val pairAssertions by lazy { loadSingleService(PairAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Map],
 * which an implementation of the domain of Atrium has to provide.
 */
interface PairAssertions {
    fun <K, T : Pair<K, *>> first(expect: Expect<T>): ExtractedFeaturePostStep<T, K>
    fun <V, T : Pair<*, V>> second(expect: Expect<T>): ExtractedFeaturePostStep<T, V>


    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <K : Any> first(plant: AssertionPlant<Pair<K, *>>, assertionCreator: AssertionPlant<K>.() -> Unit): Assertion

    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <V : Any> second(plant: AssertionPlant<Pair<*, V>>, assertionCreator: AssertionPlant<V>.() -> Unit): Assertion

    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <K> nullableFirst(
        plant: AssertionPlant<Pair<K, *>>,
        assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ): Assertion

    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <V> nullableSecond(
        plant: AssertionPlant<Pair<*, V>>,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ): Assertion
}
