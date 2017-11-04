package ch.tutteli.atrium.assertions

/**
 * Represents an [IAssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Represents an [IAssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 *              invisible in reporting.
 * @param assertions The assertions of this group.
 */
class InvisibleAssertionGroup(assertions: List<IAssertion>)
    : EmptyNameAndSubjectAssertionGroup(InvisibleAssertionGroupType, assertions)
