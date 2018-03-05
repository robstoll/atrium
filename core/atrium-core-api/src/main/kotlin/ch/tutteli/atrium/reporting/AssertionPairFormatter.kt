package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a formatter of assertion pairs -- which consists of a [Translatable] and a representation.
 */
interface AssertionPairFormatter {

    /**
     * Formats the header ([name][AssertionGroup.name] and [subject][AssertionGroup.subject]) of the given
     * [assertionGroup] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
     * of the given [parameterObject].
     *
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param assertionGroup The [AssertionGroup] of which we want to format the header.
     * @param newParameterObject The [AssertionFormatterParameterObject] used for the [AssertionGroup.assertions].
     */
    fun formatGroupHeader(parameterObject: AssertionFormatterParameterObject, assertionGroup: AssertionGroup, newParameterObject: AssertionFormatterParameterObject)

    /**
     * Formats the assertion pair consisting of the given [translatable] and the given [representation]
     * and appends the result to the [sb][AssertionFormatterParameterObject.sb] of the given [parameterObject].
     *
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param translatable The description of the assertion pair.
     * @param representation The representation of the assertion pair.
     */
    fun format(parameterObject: AssertionFormatterParameterObject, translatable: Translatable, representation: Any)
}
