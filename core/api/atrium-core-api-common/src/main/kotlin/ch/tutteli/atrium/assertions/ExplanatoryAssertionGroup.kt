package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType], which means a [Reporter] should not
 * show whether the [assertions] hold or not -- moreover [holds] always returns `true`.
 *
 * @constructor Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 * @param type The concrete [ExplanatoryAssertionGroupType]
 * @param explanatoryAssertions The [assertions] of this group which shall not be evaluated but are used in reporting
 *   to explain something (rather than making assumptions).
 */
@Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)
@Deprecated("Use AssertionGroup, do not rely on this specific type, will be made internal with 0.10.0")
class ExplanatoryAssertionGroup internal constructor(
    type: ExplanatoryAssertionGroupType,
    explanatoryAssertions: List<Assertion>
) : EmptyNameAndRepresentationAssertionGroup(type, explanatoryAssertions) {

    override fun holds() = true

    /**
     * @suppress
     */
    override fun toString(): String {
        return this::class.simpleName!!
    }
}
