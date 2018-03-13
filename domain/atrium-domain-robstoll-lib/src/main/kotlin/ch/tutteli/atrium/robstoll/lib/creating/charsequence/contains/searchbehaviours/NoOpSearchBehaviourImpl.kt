package ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
class NoOpSearchBehaviourImpl : NoOpSearchBehaviour
