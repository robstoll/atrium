package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.creating.IAssertionPlant

/**
 * Represents a formatter for objects, typically the [IAssertionPlant.subject] of an [IAssertionPlant].
 */
interface IObjectFormatter {
    fun format(any: Any?): String
}
