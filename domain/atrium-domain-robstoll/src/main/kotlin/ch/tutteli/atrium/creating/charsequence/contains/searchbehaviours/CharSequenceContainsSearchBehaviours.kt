package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder

/**
 * A dummy implementation of [ICharSequenceContainsSearchBehaviours] which should be replaced by an actual implementation.
 */
object CharSequenceContainsSearchBehaviours : ICharSequenceContainsSearchBehaviours {
    override fun <T : CharSequence> containsIgnoringCase(
        containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
    ): CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour> =
        _containsIgnoringCase(containsBuilder)
}
