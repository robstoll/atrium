package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Represents a formatter for objects, typically the [IAssertionPlant.subject] of an [IAssertionPlant].
 */
interface IObjectFormatter {
    fun format(any: Any?): String
}
