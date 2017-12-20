package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Represents the search behaviour that an [CharSequenceContains.ISearcher] should ignore case
 * when searching for an expected object.
 */
object CharSequenceContainsIgnoringCaseSearchBehaviour : CharSequenceContains.SearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, description)
}
