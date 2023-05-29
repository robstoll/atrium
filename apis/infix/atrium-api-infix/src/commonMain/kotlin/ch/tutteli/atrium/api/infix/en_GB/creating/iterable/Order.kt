package ch.tutteli.atrium.api.infix.en_GB.creating.iterable

import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.groupsToList

/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>`.
 *
 * Use the function `order(group, group, ...)` to create this representation.
 *
 * Notice, most probably the type parameter G will be removed in the future, will be fixed to [Group].
 */
class Order<out T, out G : Group<T>> internal constructor(
    val firstGroup: G,
    val secondGroup: G,
    val otherExpectedGroups: Array<out G>
) {
    fun toList(): List<List<T>> = groupsToList(
        firstGroup,
        secondGroup,
        otherExpectedGroups
    )
}
