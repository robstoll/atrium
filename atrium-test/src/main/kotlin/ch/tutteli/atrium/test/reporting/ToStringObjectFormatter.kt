package ch.tutteli.atrium.test.reporting

import ch.tutteli.atrium.reporting.IObjectFormatter

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
