package ch.tutteli.assertk.reporting

class DetailedObjectFormatter : IObjectFormatter {

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
