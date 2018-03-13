package ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents still the default search behaviour but a [CharSequenceContains.Checker] should be used which verifies
 * that the [CharSequenceContains.Searcher] could not find the expected object.
 */
class NotSearchBehaviourImpl : NotSearchBehaviour {
    /**
     * Returns [DescriptionCharSequenceAssertion.CONTAINS_NOT].
     * @return [DescriptionCharSequenceAssertion.CONTAINS_NOT]
     */
    override fun decorateDescription(description: Translatable)
        = DescriptionCharSequenceAssertion.CONTAINS_NOT
}
