package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.IObjectFormatter

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
