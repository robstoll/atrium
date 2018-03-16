package ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable] but that
 * the resulting assertion should not hold if there are less entries than expected or more.
 */
interface InAnyOrderOnlySearchBehaviour : IterableContains.SearchBehaviour
