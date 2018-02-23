package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents the search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
@Deprecated("use the search behaviour from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour"))
open class CharSequenceContainsIgnoringCaseSearchBehaviour(
    private val previousBehaviour: ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
) : CharSequenceContains.SearchBehaviour,
    ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour{

    override fun decorateDescription(description: Translatable): Translatable {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, previousDecorated)
    }
}
