package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Represents the search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
open class CharSequenceContainsIgnoringCaseSearchBehaviour(
    private val previousBehaviour: CharSequenceContainsNoOpSearchBehaviour
) : CharSequenceContains.SearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, previousDecorated)
    }
}
