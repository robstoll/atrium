package ch.tutteli.atrium.domain.builders.utils

/**
 * Adds [one] and [others] into a new [List] and returns it.
 * @return a [List] containing [one] and [others].
 */
fun <T> varargToList(one: T, others: Array<out T>): List<T> {
    val list = ArrayList<T>(others.size + 1)
    list.add(one)
    list.addAll(others)
    return list
}
