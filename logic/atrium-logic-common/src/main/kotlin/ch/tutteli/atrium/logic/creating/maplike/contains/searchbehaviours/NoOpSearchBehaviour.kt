package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
interface NoOpSearchBehaviour : MapLikeContains.SearchBehaviour {
    /**
     * Returns the given [description].
     * @return The given [description].
     */
    override fun decorateDescription(description: Translatable) = description
}
