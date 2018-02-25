package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Defines the minimum set of [CharSequenceContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface ICharSequenceContainsSearchBehaviours {
    fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
}
