package ch.tutteli.atrium.assertions

/**
 * Represents an [IAssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Use [AssertionGroupBuilder.invisible] to create an [InvisibleAssertionGroup].
 * @param assertions The assertions of this group.
 */
class InvisibleAssertionGroup internal constructor(assertions: List<IAssertion>)
    : EmptyNameAndSubjectAssertionGroup(InvisibleAssertionGroupType, assertions)
