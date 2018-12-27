@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents the *deprecated* default search behaviour but uses [DescriptionCharSequenceAssertion.CONTAINS_NOT] for the description.
 */
@Deprecated(
    "use the interface NotSearchBehaviour instead; will be removed with 1.0.0",
    ReplaceWith(
        "NotSearchBehaviour",
        "ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour"
    )
)
open class CharSequenceContainsNotSearchBehaviour : CharSequenceContainsNoOpSearchBehaviour(),
    ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
{
    /**
     * Returns [DescriptionCharSequenceAssertion.CONTAINS_NOT].
     * @return [DescriptionCharSequenceAssertion.CONTAINS_NOT]
     */
    override fun decorateDescription(description: Translatable)
        = DescriptionCharSequenceAssertion.CONTAINS_NOT
}
