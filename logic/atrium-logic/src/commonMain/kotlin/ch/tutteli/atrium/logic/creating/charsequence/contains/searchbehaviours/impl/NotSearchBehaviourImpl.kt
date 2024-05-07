package ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

/**
 * Represents still the default search behaviour but a [CharSequenceContains.Checker] should be used which verifies
 * that the [CharSequenceContains.Searcher] could not find the expected object.
 */
class NotSearchBehaviourImpl : NotSearchBehaviour {
    /**
     * Returns [DescriptionCharSequenceExpectation.NOT_TO_CONTAIN].
     * @return [DescriptionCharSequenceExpectation.NOT_TO_CONTAIN]
     */
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun decorateDescription(description: ch.tutteli.atrium.reporting.translating.Translatable): ch.tutteli.atrium.reporting.translating.Translatable =
        DescriptionCharSequenceExpectation.NOT_TO_CONTAIN
}
