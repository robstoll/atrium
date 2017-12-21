package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a formatter of assertion pairs -- which consists of a [Translatable] and a representation.
 */
interface AssertionPairFormatter {
    /**
     * Formats the assertion pair consisting of the given [translatable] and the given [representation]
     * and appends the result to the [sb][AssertionFormatterMethodObject.sb] of the given [methodObject].
     *
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to which the result will be appended.
     */
    fun format(methodObject: AssertionFormatterMethodObject, translatable: Translatable, representation: Any)
}
