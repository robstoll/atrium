package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IDoNotFilterAssertionGroupType

/**
 * A method object used for interactions between [IAssertionFormatterController] and [IAssertionFormatter].
 *
 * @property sb The [StringBuilder] to which the formatted [IAssertion] will be appended.
 * @property prefix The current prefix per assertion.
 * @property indentLevel The current indentation level.
 * @property assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
 *
 * @constructor A method object used for interactions between [IAssertionFormatterController] and [IAssertionFormatter].
 * @param sb The [StringBuilder] to which the formatted [IAssertion] will be appended.
 * @param prefix The current prefix per assertion.
 * @param indentLevel The current indentation level.
 * @param assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
 */
class AssertionFormatterMethodObject private constructor(
    val sb: StringBuilder,
    val prefix: String,
    private val indentLevel: Int,
    val assertionFilter: (IAssertion) -> Boolean,
    private val numberOfDoNotFilterGroups: Int) {

    /**
     * Creates an [AssertionFormatterMethodObject] for kind of a child of the current method object using the given
     * [newPrefix] (the child will typically be used to indent [IAssertion]s one level more).
     *
     * Technically speaking it means that the child's [indentLevel] is based on the current method object but increased
     * by the current method object's [prefix].[length][String.length].
     *
     * @param newPrefix The new prefix to be used.
     *
     * @return The newly created [AssertionFormatterMethodObject].
     */
    fun createChildWithNewPrefix(newPrefix: String) = createChildWithNewPrefixAndAdditionalIndent(newPrefix, 0)


    /**
     * Creates an [AssertionFormatterMethodObject] for kind of a child of the current method object using the given
     * [newPrefix] and an [additionalIndent] (the child will typically be used to indent [IAssertion]s one level more).
     *
     * Technically speaking it means that the child's [indentLevel] is based on the current method object but increased
     * by the current method object's [prefix].[length][String.length] as well as the given [additionalIndent].
     *
     * @param newPrefix The new prefix to be used.
     * @param additionalIndent The additional indent which should be added to the current [indentLevel] next to the
     *        current [prefix].[length][String.length].
     *
     * @return The newly created [AssertionFormatterMethodObject].
     */
    fun createChildWithNewPrefixAndAdditionalIndent(newPrefix: String, additionalIndent: Int)
        = AssertionFormatterMethodObject(sb, newPrefix, indentLevel + prefix.length + additionalIndent, assertionFilter, numberOfDoNotFilterGroups)


    /**
     * Clones the current [AssertionFormatterMethodObject] and increases [numberOfDoNotFilterGroups] by one because
     * it is assumed that the resulting methodObject is used to format an [IAssertionGroup] of
     * type [IDoNotFilterAssertionGroupType].
     *
     * @return The newly created [AssertionFormatterMethodObject].
     */
    fun createForDoNotFilterAssertionGroup(): AssertionFormatterMethodObject
        = AssertionFormatterMethodObject(sb, prefix, indentLevel, assertionFilter, numberOfDoNotFilterGroups + 1)

    /**
     * Indicates that the formatting process is currently not formatting the [IAssertion]s (or any nested assertion)
     * of an [IAssertionGroup] of type [IDoNotFilterAssertionGroupType].
     *
     * @return `true` if the formatting process is currently within an explanatory assertion group; `false` otherwise.
     */
    fun isNotInDoNotFilterGroup() = numberOfDoNotFilterGroups == 0

    /**
     * Appends a new line (system separator), spaces equal to the number of [indentLevel] and the [prefix] to [sb].
     */
    fun appendLnIndentAndPrefix() {
        sb.appendln()
        indent()
        sb.append(prefix)
    }

    /**
     *  Appends spaces equal to the number of [indentLevel] to [sb].
     */
    private fun indent() {
        for (i in 0 until indentLevel) {
            sb.append(' ')
        }
    }

    companion object {
        /**
         * Creates a new [AssertionFormatterMethodObject], one without a [prefix] and with [indentLevel] = 0.
         *
         * @param sb The [StringBuilder] to which the formatting should be written.
         * @param assertionFilter The filter used to decide whether an assertion should be formatted at all or not.
         *
         * @return The newly created [AssertionFormatterMethodObject].
         */
        fun new(sb: StringBuilder, assertionFilter: (IAssertion) -> Boolean): AssertionFormatterMethodObject {

            return AssertionFormatterMethodObject(sb, "", 0, assertionFilter, numberOfDoNotFilterGroups = 0)
        }
    }


}
