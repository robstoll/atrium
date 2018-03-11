package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import java.util.*

/**
 * The access point to an implementation of [SearchBehaviourFactory].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val searchBehaviourFactory by lazy { SingleServiceLoader.load(SearchBehaviourFactory::class.java) }


/**
 * Defines the minimum set of [CharSequenceContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface SearchBehaviourFactory {

    fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
}
