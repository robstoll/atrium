package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

/**
 * Represents the search behaviour that expected entries have to appear in the given order within the [MapLike] and
 * that the resulting assertion should not hold if there are less or more entries than expected.
 */
interface InOrderOnlySearchBehaviour : MapLikeContains.SearchBehaviour
