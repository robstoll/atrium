package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion

/**
 * A method object used for interactions between [IAssertionFormatterController] and [IAssertionFormatter].
 *
 * @property sb The [StringBuilder] to which the formatted [IAssertion] will be appended.
 * @property indentLevel The current indentation level..
 * @property assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
 *
 * @constructor A method object used for interactions between [IAssertionFormatterController] and [IAssertionFormatter].
 * @param sb The [StringBuilder] to which the formatted [IAssertion] will be appended.
 * @param indentLevel The current indentation level..
 * @param assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
 */
class AssertionFormatterMethodObject(
    val sb: StringBuilder,
    val indentLevel: Int,
    val assertionFilter: (IAssertion) -> Boolean) {

    /**
     *  Appends the number equals to [indentLevel] of spaces to [sb].
     */
    fun indent() {
        for (i in 0 until indentLevel) {
            sb.append(' ')
        }
    }
}
