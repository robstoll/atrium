@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ListAssertions
import ch.tutteli.atrium.domain.creating.listAssertions

/**
 * Delegates inter alia to the implementation of [ListAssertions].
 * In detail, it implements [ListAssertions] by delegating to [listAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ListAssertionsBuilder : ListAssertions {

    override inline fun <E, T : List<E>> get(
        assertionContainer: Expect<T>,
        index: Int
    ) = listAssertions.get(assertionContainer, index)

    // everything below is deprecated functionality and will be removed with 1.0.0

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override fun <T : Any> get(plant: AssertionPlant<List<T>>, index: Int): AssertionPlant<T> =
        listAssertions.get(plant, index)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <T : Any> get(
        plant: AssertionPlant<List<T>>,
        index: Int,
        noinline assertionCreator: AssertionPlant<T>.() -> Unit
    ) = listAssertions.get(plant, index, assertionCreator)


    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override fun <T> getNullable(plant: AssertionPlant<List<T>>, index: Int): AssertionPlantNullable<T> =
        listAssertions.getNullable(plant, index)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <T> getNullable(
        plant: AssertionPlant<List<T>>,
        index: Int,
        noinline assertionCreator: AssertionPlantNullable<T>.() -> Unit
    ) = listAssertions.getNullable(plant, index, assertionCreator)
}
