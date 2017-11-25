package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Represents the search behaviour that an [ICharSequenceContains.ISearcher] should ignore case
 * when searching for an expected object.
 */
object CharSequenceContainsIgnoringCaseSearchBehaviour : ICharSequenceContains.ISearchBehaviour {

    override fun decorateDescription(description: ITranslatable): ITranslatable
        = TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, description)
}
