package ch.tutteli.atrium.test.reporting

import ch.tutteli.atrium.reporting.IObjectFormatter

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(value: Any?) = value.toString()
}
