package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ListAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._get
import ch.tutteli.atrium.domain.robstoll.lib.creating._getNullable

/**
 * Robstoll's implementation of [ListAssertions].
 */
class ListAssertionsImpl : ListAssertions {

    override fun <E, T : List<E>> get(
        assertionContainer: Expect<T>,
        index: Int
    ) = _get(assertionContainer, index)

    override fun <E, T : List<E>> get(
        assertionContainer: Expect<T>,
        index: Int,
        assertionCreator: Expect<E>.() -> Unit
    ) = _get(assertionContainer, index, assertionCreator)


    override fun <T : Any> get(plant: AssertionPlant<List<T>>, index: Int): AssertionPlant<T> = _get(plant, index)

    override fun <T : Any> get(
        plant: AssertionPlant<List<T>>,
        index: Int,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ) = _get(plant, index, assertionCreator)

    override fun <T> getNullable(
        plant: AssertionPlant<List<T>>,
        index: Int,
        assertionCreator: AssertionPlantNullable<T>.() -> Unit
    ) = _getNullable(plant, index, assertionCreator)

    override fun <T> getNullable(plant: AssertionPlant<List<T>>, index: Int): AssertionPlantNullable<T> =
        _getNullable(plant, index)

}
