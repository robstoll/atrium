package ch.tutteli.assertk.reporting

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
