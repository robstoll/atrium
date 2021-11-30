package ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

/**
 * Represents still the default search behaviour but a [CharSequenceContains.Checker] should be used which verifies
 * that the [CharSequenceContains.Searcher] could not find the expected object.
 */
class NotSearchBehaviourImpl : NotSearchBehaviour {
    /**
     * Returns [DescriptionCharSequenceExpectation.CONTAINS_NOT].
     * @return [DescriptionCharSequenceExpectation.CONTAINS_NOT]
     */
    override fun decorateDescription(description: Translatable): Translatable =
        DescriptionCharSequenceExpectation.NOT_TO_CONTAIN
}
