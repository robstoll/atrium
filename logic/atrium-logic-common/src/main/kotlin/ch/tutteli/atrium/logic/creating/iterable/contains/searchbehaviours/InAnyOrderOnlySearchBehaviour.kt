package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Represents the search behaviour that expected entries can appear in any order within the [IterableLike] but that
 * the resulting assertion should not hold if there are less or more entries than expected.
 */
interface InAnyOrderOnlySearchBehaviour : IterableLikeContains.SearchBehaviour
