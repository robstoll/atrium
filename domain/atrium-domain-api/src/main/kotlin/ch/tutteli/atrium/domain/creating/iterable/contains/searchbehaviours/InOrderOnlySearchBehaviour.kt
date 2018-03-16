package ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * Represents the search behaviour that expected entries have to appear in the given order within the [Iterable] and
 * that the resulting assertion should not hold if there are less entries than expected or more.
 */
interface InOrderOnlySearchBehaviour : IterableContains.SearchBehaviour
