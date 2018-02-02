package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the default search behaviour but uses [DescriptionCharSequenceAssertion.CONTAINS_NOT] for the description.
 */
open class CharSequenceContainsNotSearchBehaviour : CharSequenceContainsNoOpSearchBehaviour() {
    /**
     * Returns [DescriptionCharSequenceAssertion.CONTAINS_NOT].
     * @return [DescriptionCharSequenceAssertion.CONTAINS_NOT]
     */
    override fun decorateDescription(description: Translatable)
        = DescriptionCharSequenceAssertion.CONTAINS_NOT
}
