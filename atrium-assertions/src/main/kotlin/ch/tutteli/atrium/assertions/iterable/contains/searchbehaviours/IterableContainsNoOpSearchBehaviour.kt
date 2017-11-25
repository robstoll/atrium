package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour.decorateDescription
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
object IterableContainsNoOpSearchBehaviour : IIterableContains.ISearchBehaviour {
    /**
     * Returns the given [description].
     * @return the given [description].
     */
    override fun decorateDescription(description: ITranslatable) = description
}
