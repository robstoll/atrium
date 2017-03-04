package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.reporting.IObjectFormatter

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
