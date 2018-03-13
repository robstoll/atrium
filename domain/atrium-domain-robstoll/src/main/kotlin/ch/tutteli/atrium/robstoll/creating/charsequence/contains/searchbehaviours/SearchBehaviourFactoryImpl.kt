package ch.tutteli.atrium.robstoll.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory
import ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.searchbehaviours._containsIgnoringCase

/**
 * Robstoll's implementation of [SearchBehaviourFactory].
 */
class SearchBehaviourFactoryImpl : SearchBehaviourFactory {

    override fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        = _containsIgnoringCase(containsBuilder)
}
