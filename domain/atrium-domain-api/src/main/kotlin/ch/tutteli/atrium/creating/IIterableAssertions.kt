package ch.tutteli.atrium.creating

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import java.util.*

/**
 * The access point to an implementation of [IterableAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val iterableAssertions by lazy { SingleServiceLoader.load(IterableAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableAssertions {
    fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour>
}
