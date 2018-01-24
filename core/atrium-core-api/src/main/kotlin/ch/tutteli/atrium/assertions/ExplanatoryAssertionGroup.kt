package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType], which means a [Reporter] should not
 * show whether the [assertions] hold or not -- moreover [holds] always returns `true`.
 *
 * @constructor Use [AssertionBuilder.explanatoryGroup] to create an [ExplanatoryAssertionGroup].
 * @param type The concrete [ExplanatoryAssertionGroupType]
 * @param explanatoryAssertions The [assertions] of this group which shall not be evaluated but are used in reporting
 *   to explain something (rather than making assumptions).
 */
class ExplanatoryAssertionGroup internal constructor(type: ExplanatoryAssertionGroupType, explanatoryAssertions: List<Assertion>)
    : EmptyNameAndSubjectAssertionGroup(type, explanatoryAssertions) {

    override fun holds() = true

    /**
     * @suppress
     */
    override fun toString(): String {
        return javaClass.simpleName
    }
}
