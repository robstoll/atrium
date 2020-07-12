//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PairAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._first
import ch.tutteli.atrium.domain.robstoll.lib.creating._nullableFirst
import ch.tutteli.atrium.domain.robstoll.lib.creating._nullableSecond
import ch.tutteli.atrium.domain.robstoll.lib.creating._second

@Deprecated("Will be removed with 1.0.0")
class PairAssertionsImpl : PairAssertions {
    override fun <K, T : Pair<K, *>> first(expect: Expect<T>) = _first(expect)
    override fun <V, T : Pair<*, V>> second(expect: Expect<T>) = _second(expect)


    override fun <K : Any> first(
        plant: AssertionPlant<Pair<K, *>>,
        assertionCreator: AssertionPlant<K>.() -> Unit
    ): Assertion = _first(plant, assertionCreator)

    override fun <V : Any> second(
        plant: AssertionPlant<Pair<*, V>>,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ): Assertion = _second(plant, assertionCreator)

    override fun <K> nullableFirst(
        plant: AssertionPlant<Pair<K, *>>,
        assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ): Assertion = _nullableFirst(plant, assertionCreator)

    override fun <V> nullableSecond(
        plant: AssertionPlant<Pair<*, V>>,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ): Assertion = _nullableSecond(plant, assertionCreator)
}
