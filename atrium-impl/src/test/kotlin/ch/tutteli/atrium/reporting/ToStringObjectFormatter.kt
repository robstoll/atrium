package ch.tutteli.atrium.reporting

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
