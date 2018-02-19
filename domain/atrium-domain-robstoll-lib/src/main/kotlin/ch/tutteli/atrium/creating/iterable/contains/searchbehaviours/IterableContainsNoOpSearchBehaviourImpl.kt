package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
class IterableContainsNoOpSearchBehaviourImpl: IterableContainsNoOpSearchBehaviour
