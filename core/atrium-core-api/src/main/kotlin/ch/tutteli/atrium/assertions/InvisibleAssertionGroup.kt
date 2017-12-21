package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with an [DefaultInvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Use [AssertionGroup.Builder.invisible] to create an [InvisibleAssertionGroup].
 * @param assertions The assertions of this group.
 */
class InvisibleAssertionGroup internal constructor(assertions: List<Assertion>)
    : EmptyNameAndSubjectAssertionGroup(DefaultInvisibleAssertionGroupType, assertions)
