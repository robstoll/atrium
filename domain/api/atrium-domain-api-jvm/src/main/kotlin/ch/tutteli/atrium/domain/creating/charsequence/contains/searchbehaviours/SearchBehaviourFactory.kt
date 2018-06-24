package ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * The access point to an implementation of [SearchBehaviourFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val searchBehaviourFactory by lazy { loadSingleService(SearchBehaviourFactory::class) }


/**
 * Defines the minimum set of [CharSequenceContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface SearchBehaviourFactory {

    fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
}
