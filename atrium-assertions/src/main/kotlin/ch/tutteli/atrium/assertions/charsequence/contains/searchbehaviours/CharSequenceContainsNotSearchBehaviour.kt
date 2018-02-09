package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents the default search behaviour but uses [DescriptionCharSequenceAssertion.CONTAINS_NOT] for the description.
 */
@Deprecated("use the search behaviour from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour"))
open class CharSequenceContainsNotSearchBehaviour : CharSequenceContainsNoOpSearchBehaviour() {
    /**
     * Returns [DescriptionCharSequenceAssertion.CONTAINS_NOT].
     * @return [DescriptionCharSequenceAssertion.CONTAINS_NOT]
     */
    override fun decorateDescription(description: Translatable)
        = DescriptionCharSequenceAssertion.CONTAINS_NOT
}
