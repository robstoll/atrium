package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Robstoll's implementation of [ICharSequenceContainsSearchBehaviours].
 */
object CharSequenceContainsSearchBehaviours : ICharSequenceContainsSearchBehaviours {
    override fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
        = _containsIgnoringCase(containsBuilder)
}
