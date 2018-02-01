package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with a [DefaultIndentAssertionGroupType], which means the [assertions] shall be
 * indented one extra level and [name] and [subject] shall be neglected (not reported to the output).
 *
 * @constructor Represents an [AssertionGroup] with a [DefaultIndentAssertionGroupType], which means the [assertions] shall
 *   be indented one extra level and [name] and [subject] shall be neglected (not reported to the output).
 * @param assertions The assertions of this group.
 */
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). See AssertionBuilder for different groups. Will be removed with 1.0.0")
class IndentAssertionGroup(assertions: List<Assertion>)
    : EmptyNameAndSubjectAssertionGroup(DefaultIndentAssertionGroupType, assertions)
