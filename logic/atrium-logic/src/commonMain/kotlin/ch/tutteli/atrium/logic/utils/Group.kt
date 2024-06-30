package ch.tutteli.atrium.logic.utils

/**
 * Represents a group of [T].
 */
//TODO 1.3.0 deprecate everything

interface Group<out T> {
    /**
     * Returns the members of the group as [List].
     */
    fun toList(): List<T>
}
