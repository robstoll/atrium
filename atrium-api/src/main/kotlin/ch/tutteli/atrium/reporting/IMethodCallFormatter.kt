package ch.tutteli.atrium.reporting

import kotlin.reflect.KCallable

/**
 * Responsible to format a method call in reporting.
 */
interface IMethodCallFormatter {

    /**
     * Returns a lazy representation of the method call to [method] with the given [arguments].
     *
     * @return An lambda containing the logic to build the representation
     */
    fun format(method: KCallable<*>, arguments: Array<out Any?> ): () -> String
}
