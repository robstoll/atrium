package ch.tutteli.assertk

class DetailedObjectFormatter : IObjectFormatter {

    override fun format(any: Any?): String = when (any) {
        null -> "null"
        is CharSequence -> format(any)
        is Class<*> -> format(any)
        else -> any.toString() + classNameAndIdentity(any)
    }

    override fun format(string: CharSequence) = "\"$string\"" + "   " + identityHash(string)
    override fun <T> format(clazz: Class<T>) = "${clazz.simpleName} (${clazz.name})"

    private fun classNameAndIdentity(any: Any)
        = "   (" + any::class.java.name + identityHash(any) + ")"

    private fun identityHash(any: Any) = "<" + System.identityHashCode(any) + ">"
}
