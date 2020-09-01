//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents still the default search behaviour but a [CharSequenceContains.Checker] should be used which verifies
 * that the [CharSequenceContains.Searcher] could not find the expected object.
 */
@Deprecated("Use class from atrium-logic; will be removed with 1.0.0")
class NotSearchBehaviourImpl : NotSearchBehaviour {
    /**
     * Returns [DescriptionCharSequenceAssertion.CONTAINS_NOT].
     * @return [DescriptionCharSequenceAssertion.CONTAINS_NOT]
     */
    override fun decorateDescription(description: Translatable): Translatable =
        DescriptionCharSequenceAssertion.CONTAINS_NOT
}
