package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Robstoll's implementation of [ISearchBehaviourFactory].
 */
object SearchBehaviourFactory : ISearchBehaviourFactory {

    override fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        = _containsIgnoringCase(containsBuilder)
}
