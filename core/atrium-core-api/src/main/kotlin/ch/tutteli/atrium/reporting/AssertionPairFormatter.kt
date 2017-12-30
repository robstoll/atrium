package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a formatter of assertion pairs -- which consists of a [Translatable] and a representation.
 */
interface AssertionPairFormatter {

    /**
     * Formats the header of an assertion group consisting of its [name] and the [subject].
     *
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to which the result will be appended.
     * @param name The [AssertionGroup.name].
     * @param subject The [AssertionGroup.subject].
     * @param newMethodObject The [AssertionFormatterMethodObject] used for the [AssertionGroup.assertions].
     */
    fun formatGroupHeader(methodObject: AssertionFormatterMethodObject, name: Translatable, subject: Any, newMethodObject: AssertionFormatterMethodObject)

    /**
     * Formats the assertion pair consisting of the given [translatable] and the given [representation]
     * and appends the result to the [sb][AssertionFormatterMethodObject.sb] of the given [methodObject].
     *
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to which the result will be appended.
     * @param translatable The description of the assertion pair.
     * @param representation The representation of the assertion pair.
     */
    fun format(methodObject: AssertionFormatterMethodObject, translatable: Translatable, representation: Any)
}
