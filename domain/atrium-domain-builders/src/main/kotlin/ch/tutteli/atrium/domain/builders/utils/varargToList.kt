package ch.tutteli.atrium.domain.builders.utils

/**
 * Adds the given [arg] and the [otherArgs] into a new [List] and returns it.
 * @return a [List] containing [arg] and [otherArgs].
 */
fun <T> varargToList(arg: T, otherArgs: Array<out T>): List<T> {
    val list = ArrayList<T>(otherArgs.size + 1)
    list.add(arg)
    list.addAll(otherArgs)
    return list
}
