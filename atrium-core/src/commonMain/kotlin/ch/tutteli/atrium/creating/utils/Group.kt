package ch.tutteli.atrium.creating.utils

/**
 * Represents a group of [T].
 *
 * @since 1.3.0
 */
interface Group<out T> {
    /**
     * Returns the members of the group as [List].
     *
     * @since 1.3.0
     */
    fun toList(): List<T>
}
