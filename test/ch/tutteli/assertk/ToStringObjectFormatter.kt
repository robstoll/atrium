package ch.tutteli.assertk

import ch.tutteli.assertk.reporting.IObjectFormatter

class ToStringObjectFormatter : IObjectFormatter {
    override fun format(any: Any?) = any.toString()
}
