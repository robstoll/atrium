package ch.tutteli.assertk

interface IObjectFormatter {
    fun format(any: Any?): String
    fun format(string: CharSequence): String
    fun <T> format(clazz: Class<T>): String
}
