package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion

/**
 * A method object used for interactions between [IAssertionFormatterController] and [IAssertionFormatter].
 *
 * @property sb The [StringBuilder] to which the formatted [IAssertion] will be appended.
 * @property indentLevel The current indentation level..
 * @property assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
 */
class AssertionFormatterMethodObject(
    val sb: StringBuilder,
    val indentLevel: Int,
    val assertionFilter: (IAssertion) -> Boolean)
