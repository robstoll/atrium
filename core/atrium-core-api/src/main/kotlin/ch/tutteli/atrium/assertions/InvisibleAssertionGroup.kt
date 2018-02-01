package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with a [DefaultInvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Use [AssertionGroup.Builder.invisible] to create an [InvisibleAssertionGroup].
 * @param assertions The assertions of this group.
 */
@Deprecated("use AssertionBuilder.invisibleGroup instead")
class InvisibleAssertionGroup
@Deprecated("use AssertionBuilder.invisibleGroup instead", ReplaceWith("AssertionBuilder.invisibleGroup.create(assertions)"))
internal constructor(assertions: List<Assertion>)
    : EmptyNameAndSubjectAssertionGroup(DefaultInvisibleAssertionGroupType, assertions)
