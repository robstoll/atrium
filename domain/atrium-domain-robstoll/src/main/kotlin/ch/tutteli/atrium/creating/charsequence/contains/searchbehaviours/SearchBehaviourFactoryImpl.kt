package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Robstoll's implementation of [SearchBehaviourFactory].
 */
class SearchBehaviourFactoryImpl : SearchBehaviourFactory {

    override fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        = _containsIgnoringCase(containsBuilder)
}
