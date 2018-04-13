package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.ObjectFormatter

/**
 * Represents an [ObjectFormatter] which merely formats a given object by calling its [toString][String.toString]
 * method.
 *
 * This class is intended to be used in tests and not in production.
 */
object ToStringObjectFormatter : ObjectFormatter {
    override fun format(value: Any?): String {
        return if (value is LazyRepresentation) {
            format(value.eval())
        } else {
            value.toString()
        }
    }
}
