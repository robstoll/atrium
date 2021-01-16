//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.utils

/**
 * Represents a group of [T].
 */
@Deprecated(
    "Use Group from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.Group<T>")
)
interface Group<out T> {
    /**
     * Returns the members of the group as [List].
     */
    fun toList(): List<T>
}

/**
 * Represents a group of [T] (where `T: Any`) which can be converted to a [List]`<T>`
 */
@Deprecated(
    "Use super-type Group instead; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.Group<T>")
)
interface GroupWithoutNullableEntries<out T> : Group<T>

/**
 * Represents a group of [T] (where `T: Any?`) which can be converted to a [List]`<T>`
 */
@Deprecated(
    "Use super-type Group instead; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.Group<T>")
)
interface GroupWithNullableEntries<out T : Any?> : Group<T>

/**
 * Adds the given [firstGroup], the [secondGroup] and the [otherGroups] into a new [List] and returns it.
 * @return a [List] containing [firstGroup], [secondGroup] and [otherGroups].
 */
@Deprecated(
    "Use groupsToList from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.groupsToList<T>(firstGroup, secondGroup, otherGroups)")
)
fun <T> groupsToList(firstGroup: Group<T>, secondGroup: Group<T>, otherGroups: Array<out Group<T>>): List<List<T>> {
    val groups = ArrayList<List<T>>(otherGroups.size + 2)
    requireNotEmptyAndAdd(groups, firstGroup)
    requireNotEmptyAndAdd(groups, secondGroup)
    otherGroups.forEach { requireNotEmptyAndAdd(groups, it) }
    return groups
}

private fun <T> requireNotEmptyAndAdd(groups: ArrayList<List<T>>, group: Group<T>) {
    val list = group.toList()
    require(list.isNotEmpty()) {
        "a group of values cannot be empty."
    }
    groups.add(list)
}
