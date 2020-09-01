//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents the search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
@Deprecated("Use class from atrium-logic; will be removed with 1.0.0")
class IgnoringCaseSearchBehaviourImpl(
    private val previousBehaviour: NoOpSearchBehaviour
) : IgnoringCaseSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, previousDecorated)
    }
}
