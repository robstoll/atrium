package ch.tutteli.atrium.reporting

/**
 * Responsible to format a method call in reporting.
 */
interface MethodCallFormatter {

    /**
     * Returns a lazy representation of the method call to a method named [methodName] with the given [arguments].
     *
     * @param methodName The name of the method for which a call with the given [arguments] should be formatted.
     * @param arguments The arguments of the method call.
     *
     * @return An lambda containing the logic to build the representation.
     */
    fun format(methodName: String, arguments: Array<out Any?>): () -> String
}
