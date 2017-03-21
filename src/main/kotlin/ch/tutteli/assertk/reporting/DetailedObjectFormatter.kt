package ch.tutteli.assertk.reporting

/**
 * Formats an object by using its [toString] representation, its [Class.name] and its [System.identityHashCode].
 *
 * The aim of representing more information than just [toString] is to avoid situations where an assert may fail and [toString] does not distinguish two compared objects.
 * For instance, "error, assert: 1 to be 1" would not be very helpful "error, assert: 1 (Int <123>) to be 1 (Double <456>)" on the other hand is helpful.
 *
 *
 */
class DetailedObjectFormatter : IObjectFormatter {

    /**
     * The following rules apply for the representation of an object:
     * - `null` is represented as [RawString.NULL]
     * - [String] is put in quotes and its [Class.name] is omitted
     * - [CharSequence] is put in quotes, but [Class.name] is used in contrast to [String]
     * - [Class] is represented as "[Class.getSimpleName] ([Class.name])"
     * - all other objects are represented as "[toString] ([Class.name] <[System.identityHashCode]>)"
     */
    override fun format(any: Any?): String = when (any) {
        null -> RawString.NULL
        is RawString -> any.string
        is String -> format(any)
        is CharSequence -> format(any)
        is Class<*> -> format(any)
        else -> any.toString() + INDENT + classNameAndIdentity(any)
    }

    private fun format(string: String) = "\"$string\"" + INDENT + identityHash(string)
    private fun format(charSequence: CharSequence) = "\"$charSequence\"" + INDENT + classNameAndIdentity(charSequence)
    private fun <T> format(clazz: Class<T>) = "${clazz.simpleName} (${clazz.name})"

    private fun classNameAndIdentity(any: Any)
        = "(${any::class.java.name} ${identityHash(any)})"

    private fun identityHash(any: Any) = "<${System.identityHashCode(any)}>"

    companion object {
        private const val INDENT: String = "   "
    }
}
