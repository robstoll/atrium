package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *
 * @constructor Use [AssertionGroup.Builder.invisible] to create an [InvisibleAssertionGroup].
 * @param assertions The assertions of this group.
 */
@Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)
@Deprecated("Use AssertionGroup, do not rely on this specific type, will be made internal with 0.10.0")
class InvisibleAssertionGroup internal constructor(assertions: List<Assertion>) :
    EmptyNameAndRepresentationAssertionGroup(InvisibleAssertionGroupType, assertions)
