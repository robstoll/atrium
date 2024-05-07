package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * Represents a formatter of assertion pairs -- which consists of a [ch.tutteli.atrium.reporting.translating.Translatable] and a representation.
 */
//TODO 1.3.0 deprecate as well? if not adopt description in KDOC
interface AssertionPairFormatter {

    /**
     * Formats the header ([AssertionGroup.description] and [AssertionGroup.representation])
     * of the given [assertionGroup] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
     * of the given [parameterObject].
     *
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param assertionGroup The [AssertionGroup] of which we want to format the header.
     * @param newParameterObject The [AssertionFormatterParameterObject] used for the [AssertionGroup.assertions].
     */
    fun formatGroupHeader(
        parameterObject: AssertionFormatterParameterObject,
        assertionGroup: AssertionGroup,
        newParameterObject: AssertionFormatterParameterObject
    )

    /**
     * Formats the assertion pair consisting of the given [description] and the given [representation]
     * and appends the result to the [sb][AssertionFormatterParameterObject.sb] of the given [parameterObject].
     *
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param description The description of the assertion pair.
     * @param representation The representation of the assertion pair.
     */
    //TODO 1.3.0 replace description: String with Text once we have Reportable in place
    fun format(parameterObject: AssertionFormatterParameterObject, description: String, representation: Any)

    /**
     * Formats the assertion pair consisting of the given [translatable] and the given [representation]
     * and appends the result to the [sb][AssertionFormatterParameterObject.sb] of the given [parameterObject].
     *
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param translatable The description of the assertion pair.
     * @param representation The representation of the assertion pair.
     */
    @Deprecated("passed translator is ignored, use the other overload instead, Translation support was dropped with Atrium 1.3.0 and related classes will be removed with 2.0.0 at the latest",
        ReplaceWith("format(parameterObject, translatable.getDefault(), representation)")
    )
    @Suppress("DEPRECATION")
    fun format(parameterObject: AssertionFormatterParameterObject, translatable: ch.tutteli.atrium.reporting.translating.Translatable, representation: Any) =
        format(parameterObject, translatable.getDefault(), representation)

}
