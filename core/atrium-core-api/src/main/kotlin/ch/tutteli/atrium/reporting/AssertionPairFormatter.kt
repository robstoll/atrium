package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a formatter of assertion pairs -- which consists of a [Translatable] and a representation.
 */
interface AssertionPairFormatter {

    /**
     * Formats the header ([name][AssertionGroup.name] and [subject][AssertionGroup.subject]) of the given
     * [assertionGroup] and appends the result to the [sb][AssertionFormatterMethodObject.sb]
     * of the given [methodObject].
     *
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *   to which the result will be appended.
     * @param assertionGroup The [AssertionGroup] of which we want to format the header.
     * @param newMethodObject The [AssertionFormatterMethodObject] used for the [AssertionGroup.assertions].
     */
    fun formatGroupHeader(methodObject: AssertionFormatterMethodObject, assertionGroup: AssertionGroup, newMethodObject: AssertionFormatterMethodObject)

    /**
     * Formats the assertion pair consisting of the given [translatable] and the given [representation]
     * and appends the result to the [sb][AssertionFormatterMethodObject.sb] of the given [methodObject].
     *
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *   to which the result will be appended.
     * @param translatable The description of the assertion pair.
     * @param representation The representation of the assertion pair.
     */
    fun format(methodObject: AssertionFormatterMethodObject, translatable: Translatable, representation: Any)
}
