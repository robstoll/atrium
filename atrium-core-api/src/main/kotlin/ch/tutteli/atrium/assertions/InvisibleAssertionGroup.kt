package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents an [IAssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Represents an [IAssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 *              invisible in reporting.
 * @param assertions The assertions of this group.
 */
class InvisibleAssertionGroup(override val assertions: List<IAssertion>) : IAssertionGroup {
    /**
     * [Untranslatable] of an empty string.
     */
    override val name: ITranslatable = Untranslatable("")
    /**
     * [InvisibleAssertionGroupType]
     */
    override val type: IAssertionGroupType = InvisibleAssertionGroupType
    /**
     * The empty string.
     */
    override val subject: Any = RawString("")

    /**
     * @suppress
     */
    override fun toString(): String {
        return InvisibleAssertionGroup::class.simpleName + " " + assertions
    }
}
