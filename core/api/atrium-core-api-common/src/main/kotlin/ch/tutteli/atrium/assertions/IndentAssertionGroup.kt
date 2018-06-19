package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with a [DefaultIndentAssertionGroupType], which means the [assertions] shall be
 * indented one extra level and [description] and [representation] shall be neglected (not reported to the output).
 *
 * @constructor Represents an [AssertionGroup] with a [DefaultIndentAssertionGroupType], which means the [assertions] shall
 *   be indented one extra level and [description] and [representation] shall be neglected (not reported to the output).
 * @param assertions The assertions of this group.
 */
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). See AssertImpl.builder for different groups. Will be removed with 1.0.0")
class IndentAssertionGroup(assertions: List<Assertion>)
    : EmptyNameAndRepresentationAssertionGroup(DefaultIndentAssertionGroupType, assertions)
