package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect

/**
 * The access point to an implementation of [ListAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val listAssertions by lazy { loadSingleService(ListAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [List],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ListAssertions {

    fun <E, T : List<E>> get(
        assertionContainer: Expect<T>,
        index: Int
    ): Expect<E>

    fun <E, T : List<E>> get(
        assertionContainer: Expect<T>,
        index: Int,
        assertionCreator: Expect<E>.() -> Unit
    ): Assertion


    fun <T : Any> get(plant: AssertionPlant<List<T>>, index: Int): AssertionPlant<T>
    fun <T : Any> get(
        plant: AssertionPlant<List<T>>,
        index: Int,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ): Assertion

    fun <T> getNullable(plant: AssertionPlant<List<T>>, index: Int): AssertionPlantNullable<T>
    fun <T> getNullable(
        plant: AssertionPlant<List<T>>,
        index: Int,
        assertionCreator: AssertionPlantNullable<T>.() -> Unit
    ): Assertion
}

