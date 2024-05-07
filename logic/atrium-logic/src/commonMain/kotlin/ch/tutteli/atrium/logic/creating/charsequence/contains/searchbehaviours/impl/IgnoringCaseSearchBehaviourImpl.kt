package ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.IGNORING_CASE

/**
 * Represents the search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
class IgnoringCaseSearchBehaviourImpl(
    private val previousBehaviour: NoOpSearchBehaviour
) : IgnoringCaseSearchBehaviour {

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun decorateDescription(description: ch.tutteli.atrium.reporting.translating.Translatable): ch.tutteli.atrium.reporting.translating.Translatable {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(IGNORING_CASE, previousDecorated)
    }
}
