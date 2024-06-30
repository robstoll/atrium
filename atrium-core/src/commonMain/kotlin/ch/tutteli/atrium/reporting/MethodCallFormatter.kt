package ch.tutteli.atrium.reporting

/**
 * Responsible to format a method call in reporting.
 */
//TODO 1.3.0 deprecate?
interface MethodCallFormatter {
    /**
     * Returns a representation of a method call to a method named [methodName] with the given [arguments].
     *
     * @param methodName The name of the method for which a call with the given [arguments] should be formatted.
     * @param arguments The arguments of the method call.
     *
     * @return A lambda containing the logic to build the representation.
     */
    fun formatCall(methodName: String, arguments: Array<out Any?>): String

    /**
     * Formats the given [argument].
     */
    fun formatArgument(argument: Any?): String
}
