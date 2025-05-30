package ch.tutteli.atrium.logic.utils

//TODO 1.3.0 deprecate everything

/**
 * Adds the given [firstGroup], the [secondGroup] and the [otherGroups] into a new [List] and returns it.
 * @return a [List] containing [firstGroup], [secondGroup] and [otherGroups].
 */
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
