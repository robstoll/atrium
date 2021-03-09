//package ch.tutteli.atrium.reporting
//
//import ch.tutteli.atrium.assertions.Assertion
//import ch.tutteli.atrium.assertions.AssertionGroup
//import ch.tutteli.atrium.assertions.DoNotFilterAssertionGroupType
//import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
//import ch.tutteli.atrium.core.polyfills.appendln
//
///**
// * A parameter object used for interactions between [FormatterController] and [Formatter]s.
// *
// * @param sb The [StringBuilder] to which the formatted [Reportable] will be appended.
// * @param prefix The current prefix per assertion.
// * @param indentLevel The current indentation level.
// * @param filter Can be used used to filter out [Reportable]s which should not be formatted.
// */
//class FormatterParameterObject private constructor(
//    val sb: StringBuilder,
//    val prefix: String,
//    private val indentLevel: Int,
//    val filter: (Reportable) -> Boolean,
//    private val numberOfDoNotFilterGroups: Int,
//    private val numberOfExplanatoryGroups: Int
//) {
//
//    /**
//     * Creates an [FormatterParameterObject] for kind of a child of the current parameter object using the given
//     * [newPrefix] (the child will typically be used to indent [Assertion]s one level more).
//     *
//     * Technically speaking it means that the child's [indentLevel] is based on the current parameter object but increased
//     * by the current parameter object's [prefix].[length][String.length].
//     *
//     * @param newPrefix The new prefix to be used.
//     *
//     * @return The newly created [FormatterParameterObject].
//     */
//    fun createChildWithNewPrefix(newPrefix: String) =
//        createChildWithNewPrefixAndAdditionalIndent(newPrefix, additionalIndent = 0)
//
//
//    /**
//     * Creates an [FormatterParameterObject] for kind of a child of the current parameter object using the given
//     * [newPrefix] and an [additionalIndent] (the child will typically be used to indent [Assertion]s one level more).
//     *
//     * Technically speaking it means that the child's [indentLevel] is based on the current parameter object but increased
//     * by the current parameter object's [prefix].[length][String.length] as well as the given [additionalIndent].
//     *
//     * @param newPrefix The new prefix to be used.
//     * @param additionalIndent The additional indent which should be added to the current [indentLevel] next to the
//     *   current [prefix].[length][String.length].
//     *
//     * @return The newly created [FormatterParameterObject].
//     */
//    fun createChildWithNewPrefixAndAdditionalIndent(newPrefix: String, additionalIndent: Int) =
//        FormatterParameterObject(
//            sb,
//            newPrefix,
//            indentLevel + prefix.length + additionalIndent,
//            filter,
//            numberOfDoNotFilterGroups,
//            numberOfExplanatoryGroups
//        )
//
//
//    /**
//     * Clones the current [FormatterParameterObject] and increases [numberOfDoNotFilterGroups] by one because
//     * it is assumed that the resulting parameter object is used to format an [AssertionGroup] of
//     * type [DoNotFilterAssertionGroupType].
//     *
//     * @return The newly created [FormatterParameterObject].
//     */
//    fun createForDoNotFilterAssertionGroup(): FormatterParameterObject =
//        FormatterParameterObject(
//            sb,
//            prefix,
//            indentLevel,
//            filter,
//            numberOfDoNotFilterGroups + 1,
//            numberOfExplanatoryGroups
//        )
//
//
//    /**
//     * Clones the current [FormatterParameterObject] and increases [numberOfDoNotFilterGroups] by one because
//     * it is assumed that the resulting parameter object is used to format an [AssertionGroup] of
//     * type [DoNotFilterAssertionGroupType].
//     *
//     * @return The newly created [FormatterParameterObject].
//     */
//    fun createForExplanatoryFilterAssertionGroup(newPrefix : String = prefix): FormatterParameterObject =
//        FormatterParameterObject(
//            sb,
//            newPrefix,
//            indentLevel,
//            filter,
//            numberOfDoNotFilterGroups + 1,
//            numberOfExplanatoryGroups + 1
//        )
//
//
//    /**
//     * Indicates that the formatting process is currently not formatting the [Assertion]s (or any nested assertion)
//     * of an [AssertionGroup] of type [DoNotFilterAssertionGroupType].
//     *
//     * @return `true` if the formatting process is currently within an do not filter assertion group; `false` otherwise.
//     */
//    fun isNotInDoNotFilterGroup() = numberOfDoNotFilterGroups == 0
//
//
//    /**
//     * Indicates that the formatting process is currently not formatting the [Assertion]s (or any nested assertion)
//     * of an [AssertionGroup] of type [ExplanatoryAssertionGroupType].
//     *
//     * @return `true` if the formatting process is currently within an explanatory assertion group; `false` otherwise.
//     */
//    fun isNotInExplanatoryFilterGroup() = numberOfExplanatoryGroups == 0
//
//    /**
//     * Appends a new line (system separator), spaces equal to the number of [indentLevel] and the [prefix] to [sb].
//     */
//    fun appendLnIndentAndPrefix() {
//        sb.appendln()
//        indent()
//        sb.append(prefix)
//    }
//
//    /**
//     * Appends a new line (system separator), spaces equal to the number of [indentLevel] and the [prefix] to [sb].
//     */
//    fun appendLnAndIndent() {
//        sb.appendln()
//        indent()
//    }
//
//    /**
//     *  Appends spaces equal to the number of [indentLevel] to [sb].
//     */
//    private fun indent() = indent(indentLevel)
//
//    /**
//     *  Appends spaces equal to [numberOfSpaces] to [sb].
//     */
//    fun indent(numberOfSpaces: Int) {
//        for (i in 0 until numberOfSpaces) {
//            sb.append(' ')
//        }
//    }
//
//    companion object {
//        /**
//         * Creates a new [FormatterParameterObject], one without a [prefix] and with [indentLevel] = 0.
//         *
//         * @param sb The [StringBuilder] to which the formatting should be written.
//         * @param filter The filter used to decide whether an assertion should be formatted at all or not.
//         *
//         * @return The newly created [FormatterParameterObject].
//         */
//        fun root(sb: StringBuilder, filter: (Reportable) -> Boolean): FormatterParameterObject {
//
//            return FormatterParameterObject(
//                sb,
//                prefix = "",
//                indentLevel = 0,
//                filter = filter,
//                numberOfDoNotFilterGroups = 0,
//                numberOfExplanatoryGroups = 0
//            )
//        }
//    }
//}
