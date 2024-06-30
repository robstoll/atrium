package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType], which means a [Reporter] should not
 * show whether the [assertions] hold or not.
 *
 * @constructor Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 * @param type The concrete [ExplanatoryAssertionGroupType]
 * @param explanatoryAssertions The [assertions] of this group which shall not be evaluated but are used in reporting
 *   to explain something (rather than making assumptions).
 */
internal class ExplanatoryAssertionGroup(
    type: ExplanatoryAssertionGroupType,
    explanatoryAssertions: List<Assertion>,
    private val holds: Boolean
) : EmptyNameAndRepresentationAssertionGroup(type, explanatoryAssertions) {

    override fun holds() = holds

    /**
     * @suppress
     */
    override fun toString(): String {
        return this::class.simpleName!!
    }

    override val children: List<Reportable> get() = assertions
}
