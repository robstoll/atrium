package ch.tutteli.atrium.logic.utils

/**
 * Represents a group of [T].
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.utils.Group")
)
interface Group<out T> {
    /**
     * Returns the members of the group as [List].
     */
    fun toList(): List<T>
}
