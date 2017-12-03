package ch.tutteli.atrium.assertions

/**
 * Represents an [IAssertionGroup] with an [IIndentAssertionGroupType], which means the [assertions] shall be
 * indented one extra level and [name] and [subject] shall be neglected (not reported to the output).
 *
 * @constructor Represents an [IAssertionGroup] with an [IIndentAssertionGroupType], which means the [assertions] shall
 *              be indented one extra level and [name] and [subject] shall be neglected (not reported to the output).
 * @param assertions The assertions of this group.
 */
class IndentAssertionGroup(assertions: List<IAssertion>)
    : EmptyNameAndSubjectAssertionGroup(IndentAssertionGroupType, assertions)
