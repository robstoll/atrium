package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.throwUnsupportedOperationException

/**
 * A dummy implementation of [ICharSequenceContainsSearchBehaviours] which should be replaced by an actual implementation.
 */
object CharSequenceContainsSearchBehaviours : ICharSequenceContainsSearchBehaviours {
    override fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
        = throwUnsupportedOperationException()
}
