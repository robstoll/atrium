package ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import java.util.*

/**
 * The access point to an implementation of [SearchBehaviourFactory].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val searchBehaviourFactory by lazy { SingleServiceLoader.load(SearchBehaviourFactory::class.java) }


/**
 * Defines the minimum set of [IterableContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface SearchBehaviourFactory {

    fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>

    fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>

    fun <E, T : Iterable<E>> inOrder(
        builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderSearchBehaviour>

    fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
}
