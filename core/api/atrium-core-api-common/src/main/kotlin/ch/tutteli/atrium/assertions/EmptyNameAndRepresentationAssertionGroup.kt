package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents a base class for [AssertionGroup]s which have an empty [description] and [representation].
 *
 * @constructor Represents a base class for [AssertionGroup]s which have an empty [description] and [representation].
 * @param type The type of the group, e.g. [InvisibleAssertionGroupType].
 * @param assertions The assertions of this group.
 */
@Deprecated("Use AssertionGroup, do not rely on this specific type, will be made internal with 0.10.0")
open class EmptyNameAndRepresentationAssertionGroup
@Deprecated("Use AssertImpl.builder instead, will be made internal with 0.10.0")
constructor(
    override val type: AssertionGroupType,
    override val assertions: List<Assertion>
) : AssertionGroup {

    /**
     * [Untranslatable.EMPTY] -- an empty string as [Untranslatable].
     */
    override val description: Translatable = Untranslatable.EMPTY

    /**
     * [RawString.EMPTY] -- an empty string as [RawString].
     */
    override val representation: Any = RawString.EMPTY

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return this::class.simpleName + " " + assertions
    }
}
