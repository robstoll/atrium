package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents a base class for [AssertionGroup]s which have an empty [name] and [subject].
 *
 * @constructor Represents a base class for [AssertionGroup]s which have an empty [name] and [subject].
 * @param type The type of the group, e.g. [InvisibleAssertionGroupType].
 * @param assertions The assertions of this group.
 */
open class EmptyNameAndSubjectAssertionGroup(override val type: AssertionGroupType, override val assertions: List<Assertion>) : AssertionGroup {

    /**
     * [Untranslatable.EMPTY] -- an empty string as [Untranslatable].
     */
    override val name: Translatable = Untranslatable.EMPTY

    /**
     * [RawString.EMPTY] -- an empty string as [RawString].
     */
    override val subject: Any = RawString.EMPTY

    /**
     * @suppress
     */
    override fun toString(): String {
        return javaClass.simpleName + " " + assertions
    }
}
