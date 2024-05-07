package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains

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
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun decorateDescription(description: ch.tutteli.atrium.reporting.translating.Translatable) = description
}
