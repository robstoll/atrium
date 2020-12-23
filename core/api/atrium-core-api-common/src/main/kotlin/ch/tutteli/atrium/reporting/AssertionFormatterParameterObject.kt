package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DoNotFilterAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.polyfills.appendln

/**
 * A parameter object used for interactions between [AssertionFormatterController] and [AssertionFormatter].
 *
 * @property sb The [StringBuilder] to which the formatted [Assertion] will be appended.
 * @property prefix The current prefix per assertion.
 * @property indentLevel The current indentation level.
 * @property assertionFilter Can be used used to filter out [Assertion]s which should not be formatted.
 *
 * @constructor A parameter object used for interactions between [AssertionFormatterController] and [AssertionFormatter].
 * @param sb The [StringBuilder] to which the formatted [Assertion] will be appended.
 * @param prefix The current prefix per assertion.
 * @param indentLevel The current indentation level.
 * @param assertionFilter Can be used used to filter out [Assertion]s which should not be formatted.
 */
class AssertionFormatterParameterObject private constructor(
    val sb: StringBuilder,
    val prefix: String,
    private val indentLevel: Int,
    val assertionFilter: (Assertion) -> Boolean,
    private val numberOfDoNotFilterGroups: Int,
    private val numberOfExplanatoryGroups: Int
) {

    /**
     * Creates an [AssertionFormatterParameterObject] for kind of a child of the current parameter object using the given
     * [newPrefix] (the child will typically be used to indent [Assertion]s one level more).
     *
     * Technically speaking it means that the child's [indentLevel] is based on the current parameter object but increased
     * by the current parameter object's [prefix].[length][String.length].
     *
     * @param newPrefix The new prefix to be used.
     *
     * @return The newly created [AssertionFormatterParameterObject].
     */
    fun createChildWithNewPrefix(newPrefix: String) =
        createChildWithNewPrefixAndAdditionalIndent(newPrefix, additionalIndent = 0)


    /**
     * Creates an [AssertionFormatterParameterObject] for kind of a child of the current parameter object using the given
     * [newPrefix] and an [additionalIndent] (the child will typically be used to indent [Assertion]s one level more).
     *
     * Technically speaking it means that the child's [indentLevel] is based on the current parameter object but increased
     * by the current parameter object's [prefix].[length][String.length] as well as the given [additionalIndent].
     *
     * @param newPrefix The new prefix to be used.
     * @param additionalIndent The additional indent which should be added to the current [indentLevel] next to the
     *   current [prefix].[length][String.length].
     *
     * @return The newly created [AssertionFormatterParameterObject].
     */
    fun createChildWithNewPrefixAndAdditionalIndent(newPrefix: String, additionalIndent: Int) =
        AssertionFormatterParameterObject(
            sb,
            newPrefix,
            indentLevel + prefix.length + additionalIndent,
            assertionFilter,
            numberOfDoNotFilterGroups,
            numberOfExplanatoryGroups
        )


    /**
     * Clones the current [AssertionFormatterParameterObject] and increases [numberOfDoNotFilterGroups] by one because
     * it is assumed that the resulting parameter object is used to format an [AssertionGroup] of
     * type [DoNotFilterAssertionGroupType].
     *
     * @return The newly created [AssertionFormatterParameterObject].
     */
    fun createForDoNotFilterAssertionGroup(): AssertionFormatterParameterObject =
        AssertionFormatterParameterObject(
            sb,
            prefix,
            indentLevel,
            assertionFilter,
            numberOfDoNotFilterGroups + 1,
            numberOfExplanatoryGroups
        )


    /**
     * Clones the current [AssertionFormatterParameterObject] and increases [numberOfDoNotFilterGroups] by one because
     * it is assumed that the resulting parameter object is used to format an [AssertionGroup] of
     * type [DoNotFilterAssertionGroupType].
     *
     * @return The newly created [AssertionFormatterParameterObject].
     */
    fun createForExplanatoryFilterAssertionGroup(newPrefix : String = prefix): AssertionFormatterParameterObject =
        AssertionFormatterParameterObject(
            sb,
            newPrefix,
            indentLevel,
            assertionFilter,
            numberOfDoNotFilterGroups + 1,
            numberOfExplanatoryGroups + 1
        )


    /**
     * Indicates that the formatting process is currently not formatting the [Assertion]s (or any nested assertion)
     * of an [AssertionGroup] of type [DoNotFilterAssertionGroupType].
     *
     * @return `true` if the formatting process is currently within an do not filter assertion group; `false` otherwise.
     */
    fun isNotInDoNotFilterGroup() = numberOfDoNotFilterGroups == 0


    /**
     * Indicates that the formatting process is currently not formatting the [Assertion]s (or any nested assertion)
     * of an [AssertionGroup] of type [ExplanatoryAssertionGroupType].
     *
     * @return `true` if the formatting process is currently within an explanatory assertion group; `false` otherwise.
     */
    fun isNotInExplanatoryFilterGroup() = numberOfExplanatoryGroups == 0

    /**
     * Appends a new line (system separator), spaces equal to the number of [indentLevel] and the [prefix] to [sb].
     */
    fun appendLnIndentAndPrefix() {
        sb.appendln()
        indent()
        sb.append(prefix)
    }

    /**
     * Appends a new line (system separator), spaces equal to the number of [indentLevel] and the [prefix] to [sb].
     */
    fun appendLnAndIndent() {
        sb.appendln()
        indent()
    }

    /**
     *  Appends spaces equal to the number of [indentLevel] to [sb].
     */
    private fun indent() = indent(indentLevel)

    /**
     *  Appends spaces equal to [numberOfSpaces] to [sb].
     */
    fun indent(numberOfSpaces: Int) {
        for (i in 0 until numberOfSpaces) {
            sb.append(' ')
        }
    }

    companion object {
        /**
         * Creates a new [AssertionFormatterParameterObject], one without a [prefix] and with [indentLevel] = 0.
         *
         * @param sb The [StringBuilder] to which the formatting should be written.
         * @param assertionFilter The filter used to decide whether an assertion should be formatted at all or not.
         *
         * @return The newly created [AssertionFormatterParameterObject].
         */
        fun new(sb: StringBuilder, assertionFilter: (Assertion) -> Boolean): AssertionFormatterParameterObject {

            return AssertionFormatterParameterObject(
                sb,
                prefix = "",
                indentLevel = 0,
                assertionFilter = assertionFilter,
                numberOfDoNotFilterGroups = 0,
                numberOfExplanatoryGroups = 0
            )
        }
    }
}
