package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
@Deprecated("use the search behaviour from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour"))
open class IterableContainsNoOpSearchBehaviour : IterableContains.SearchBehaviour {
    /**
     * Returns the given [description].
     * @return the given [description].
     */
    override fun decorateDescription(description: Translatable) = description
}
