package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *l
 * @param assertions The assertions of this group.
 */
internal class InvisibleAssertionGroup(assertions: List<Assertion>) :
    EmptyNameAndRepresentationAssertionGroup(InvisibleAssertionGroupType, assertions)
