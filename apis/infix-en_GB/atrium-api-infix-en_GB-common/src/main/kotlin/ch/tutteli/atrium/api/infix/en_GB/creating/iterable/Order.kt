package ch.tutteli.atrium.api.infix.en_GB.creating.iterable

import ch.tutteli.atrium.domain.builders.utils.Group

//TODO #63 introduce function in addition
/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 *
 * Notice, most probably the type parameter G will be removed in the future, will be fixed to [Group].
 */
class Order<out T, out G : Group<T>>(
    val firstGroup: G,
    val secondGroup: G,
    vararg val otherExpectedGroups: G
)
