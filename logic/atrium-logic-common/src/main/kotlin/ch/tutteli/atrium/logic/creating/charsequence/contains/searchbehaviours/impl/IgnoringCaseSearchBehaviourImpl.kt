package ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents the search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
class IgnoringCaseSearchBehaviourImpl(
    private val previousBehaviour: NoOpSearchBehaviour
) : IgnoringCaseSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, previousDecorated)
    }
}
