@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PairAssertions
import ch.tutteli.atrium.domain.creating.pairAssertions

/**
 * Delegates inter alia to the implementation of [PairAssertions].
 * In detail, it implements [PairAssertions] by delegating to [pairAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object PairAssertionsBuilder : PairAssertions {

    override inline fun <K, T : Pair<K, *>> first(assertionContainer: Expect<T>) =
        pairAssertions.first(assertionContainer)

    override inline fun <V, T : Pair<*, V>> second(assertionContainer: Expect<T>) =
        pairAssertions.second(assertionContainer)

    @Suppress("DEPRECATION", "OverridingDeprecatedMember")
    override inline fun <K : Any> first(
        plant: AssertionPlant<Pair<K, *>>,
        noinline assertionCreator: AssertionPlant<K>.() -> Unit
    ): Assertion = pairAssertions.first(plant, assertionCreator)

    @Suppress("DEPRECATION", "OverridingDeprecatedMember")
    override inline fun <V : Any> second(
        plant: AssertionPlant<Pair<*, V>>,
        noinline assertionCreator: AssertionPlant<V>.() -> Unit
    ): Assertion = pairAssertions.second(plant, assertionCreator)

    @Suppress("DEPRECATION", "OverridingDeprecatedMember")
    override inline fun <K> nullableFirst(
        plant: AssertionPlant<Pair<K, *>>,
        noinline assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ): Assertion = pairAssertions.nullableFirst(plant, assertionCreator)

    @Suppress("DEPRECATION", "OverridingDeprecatedMember")
    override inline fun <V> nullableSecond(
        plant: AssertionPlant<Pair<*, V>>,
        noinline assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ): Assertion = pairAssertions.nullableSecond(plant, assertionCreator)
}
