package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.IReporter

/**
 * Represents an [IAssertionGroup] with an [ExplanatoryAssertionGroupType], which means an [IReporter] should not
 * show whether the [assertions] hold or not -- moreover [holds] always returns `true`.
 *
 * @constructor Represents an [IAssertionGroup] with an [ExplanatoryAssertionGroupType], which means an [IReporter]
 *              should not show whether the [assertions] hold or not -- moreover [holds] always returns `true`.
 * @param type The concrete [IExplanatoryAssertionGroupType]
 * @param explanatoryAssertions The [assertions] of this group which shall not be evaluated but are used in reporting
 *        to explain something (rather than making assumptions).
 */
class ExplanatoryAssertionGroup(type: IExplanatoryAssertionGroupType,explanatoryAssertions: List<IAssertion>)
    : EmptyNameAndSubjectAssertionGroup(type, explanatoryAssertions) {

    override fun holds() = true

    /**
     * @suppress
     */
    override fun toString(): String {
        return javaClass.simpleName
    }
}
