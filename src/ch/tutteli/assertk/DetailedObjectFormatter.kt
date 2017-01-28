package ch.tutteli.assertk

class DetailedObjectFormatter : IObjectFormatter {

    override fun format(any: Any?): String = when (any) {
        null -> "null"
        is CharSequence -> format(any)
        is Class<*> -> format(any)
        else -> internalFormat(any)
    }

    override fun format(string: CharSequence) = "\"$string\""
    override fun <T> format(clazz: Class<T>) = "${clazz.simpleName} (${clazz.name})"

    private fun internalFormat(any: Any)
        = any.toString() + "   (" + any.javaClass.name + "<" + System.identityHashCode(any) + ">)"
}
