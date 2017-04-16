package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.reporting.IObjectFormatter

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(value: Any?) = value.toString()
}
