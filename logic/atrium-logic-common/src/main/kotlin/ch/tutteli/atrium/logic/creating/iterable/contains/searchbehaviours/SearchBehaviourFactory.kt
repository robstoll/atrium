package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains

/**
 * The access point to an implementation of [SearchBehaviourFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val searchBehaviourFactory by lazy { loadSingleService(SearchBehaviourFactory::class) }


/**
 * Defines the minimum set of [IterableLikeContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface SearchBehaviourFactory {

    fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour>
    ): IterableLikeContains.EntryPointStep<E, T, InAnyOrderSearchBehaviour>

    fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableLikeContains.EntryPointStep<E, T, InAnyOrderSearchBehaviour>
    ): IterableLikeContains.EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>

    fun <E, T : Iterable<E>> inOrder(
        builder: IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour>
    ): IterableLikeContains.EntryPointStep<E, T, InOrderSearchBehaviour>

    fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableLikeContains.EntryPointStep<E, T, InOrderSearchBehaviour>
    ): IterableLikeContains.EntryPointStep<E, T, InOrderOnlySearchBehaviour>

    fun <E, T : Iterable<E>> inOrderOnlyGrouped(
        builder: IterableLikeContains.EntryPointStep<E, T, InOrderOnlySearchBehaviour>
    ): IterableLikeContains.EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour>

    fun <E, T : Iterable<E>> inOrderOnlyGroupedWithin(
        builder: IterableLikeContains.EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour>
    ): IterableLikeContains.EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour>
}
