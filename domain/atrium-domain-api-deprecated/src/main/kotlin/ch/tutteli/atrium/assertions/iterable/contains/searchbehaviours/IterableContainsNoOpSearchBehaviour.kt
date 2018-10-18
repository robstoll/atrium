@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains

/**
 * Represents the *deprecated* default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
@Deprecated(
    "use the interface NoOpSearchBehaviour instead, will be removed with 1.0.0",
    ReplaceWith(
        "NoOpSearchBehaviour",
        "ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour"
    )
)
open class IterableContainsNoOpSearchBehaviour : IterableContains.SearchBehaviour,
    ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
