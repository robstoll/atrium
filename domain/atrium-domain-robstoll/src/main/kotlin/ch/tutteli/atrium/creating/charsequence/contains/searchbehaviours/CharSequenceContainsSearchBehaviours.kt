package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder

/**
 * Robstoll's implementation of [ICharSequenceContainsSearchBehaviours].
 */
object CharSequenceContainsSearchBehaviours : ICharSequenceContainsSearchBehaviours {
    override fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
    ): CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
        = _containsIgnoringCase(containsBuilder)
}
