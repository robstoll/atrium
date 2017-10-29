package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents an [IAssertionGroup] with an [ExplanatoryAssertionGroupType], which means an [IReporter] should not
 * show whether the [assertions] hold or not -- moreover [holds] always returns `true`.
 *
 * @constructor Represents an [IAssertionGroup] with an [ExplanatoryAssertionGroupType], which means an [IReporter]
 *              should not show whether the [assertions] hold or not -- moreover [holds] always returns `true`.
 * @param name The name of the group.
 * @param subject The subject for which the [assertions] are defined.
 * @param assertions The assertions of this group.
 */
class ExplanatoryAssertionGroup(
    override val name: ITranslatable,
    override val subject: Any,
    override val assertions: List<IAssertion>) : IAssertionGroup {

    /**
     * [ExplanatoryAssertionGroupType]
     */
    override val type: IAssertionGroupType = ExplanatoryAssertionGroupType

    override fun holds() = true

    /**
     * @suppress
     */
    override fun toString(): String {
        return "${ExplanatoryAssertionGroup::class.simpleName} - $name: $subject"
    }
}
