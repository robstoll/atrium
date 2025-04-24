package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 *
 * @since 1.3.0
 */
interface NoOpSearchBehaviour : CharSequenceToContain.SearchBehaviour {
    /**
     * Returns the given [description].
     * @return The given [description].
     *
     * @since 1.3.0
     */
    override fun decorateDescription(description: Description) = description
}
