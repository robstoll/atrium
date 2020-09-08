package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains

/**
 * Represents the search behaviour that expected entries have to appear in the given order in terms of groups within the
 * [Iterable] whereas the entries can be in any order within the group and that the resulting assertion should not hold
 * if there are less or more entries than expected.
 */
interface InOrderOnlyGroupedSearchBehaviour : IterableLikeContains.SearchBehaviour
