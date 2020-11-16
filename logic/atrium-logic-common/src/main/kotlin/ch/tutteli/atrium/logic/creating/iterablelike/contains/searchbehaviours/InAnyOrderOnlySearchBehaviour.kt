package ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable] but that
 * the resulting assertion should not hold if there are less or more entries than expected.
 */
interface InAnyOrderOnlySearchBehaviour : IterableLikeContains.SearchBehaviour
