package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with a [DefaultInvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Use [AssertionGroup.Builder.invisible] to create an [InvisibleAssertionGroup].
 * @param assertions The assertions of this group.
 */
@Deprecated("Use AssertionGroup, do not rely on this specific type, will be made internal with 1.0.0")
class InvisibleAssertionGroup internal constructor(assertions: List<Assertion>)
    : EmptyNameAndRepresentationAssertionGroup(DefaultInvisibleAssertionGroupType, assertions)
