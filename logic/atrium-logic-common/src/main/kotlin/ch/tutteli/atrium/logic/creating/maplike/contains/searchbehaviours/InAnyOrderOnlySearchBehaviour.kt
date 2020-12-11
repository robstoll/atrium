package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours

import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

/**
 * Represents the search behaviour that expected entries can appear in any order within the [MapLike] but that
 * the resulting assertion should not hold if there are less or more entries than expected.
 */
interface InAnyOrderOnlySearchBehaviour : MapLikeContains.SearchBehaviour
