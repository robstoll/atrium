package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents the *deprecated* search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
@Deprecated(
    "use the interface IgnoringCaseSearchBehaviour instead, will be removed with 1.0.0",
    ReplaceWith(
        "IgnoringCaseSearchBehaviour",
        "ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour"
    )
)
open class CharSequenceContainsIgnoringCaseSearchBehaviour(
    private val previousBehaviour: ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
) : CharSequenceContains.SearchBehaviour,
    ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, previousDecorated)
    }
}
