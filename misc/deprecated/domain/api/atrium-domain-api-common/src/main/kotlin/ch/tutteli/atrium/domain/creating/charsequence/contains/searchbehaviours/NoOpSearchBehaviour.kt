//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
@Deprecated("Use the NoOpSearchBehaviour from atrium-logic; will be removed with 1.0.0")
interface NoOpSearchBehaviour : CharSequenceContains.SearchBehaviour {
    /**
     * Returns the given [description].
     * @return The given [description].
     */
    override fun decorateDescription(description: Translatable) = description
}
