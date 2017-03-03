package ch.tutteli.assertk

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
