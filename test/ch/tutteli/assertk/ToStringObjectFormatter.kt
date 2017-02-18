package ch.tutteli.assertk

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
    override fun format(string: CharSequence) = string.toString()
    override fun <T> format(clazz: Class<T>) = clazz.toString()
}
