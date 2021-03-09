//package ch.tutteli.atrium.reporting
//
//import ch.tutteli.atrium.assertions.Assertion
//import ch.tutteli.atrium.assertions.AssertionGroup
//
///**
// * Represents a formatter for [Reportable]s.
// */
//interface Formatter {
//
//    /**
//     * Denotes whether this [Formatter] was created to format [Reportable]s such
//     * as the given [reportable] or not.
//     *
//     * This function should be in sync with [format] and [formatGroup]. If [reportable] is an [AssertionGroup] and
//     * this method returns `true` then [formatGroup] should be able to format the given [reportable]. On the other hand,
//     * if it returns `false` then [formatGroup] should throw an [UnsupportedOperationException].
//     * The same applies for [format] where format should additionally throw an [UnsupportedOperationException]
//     * if an [AssertionGroup] is passed.
//     *
//     * @param reportable The [Reportable] which builds the basis to answer the question whether this
//     *   [Formatter] can format such types or not.
//     *
//     * @return `true` if this [Formatter] ca [format] the given [reportable]; `false` otherwise.
//     */
//    fun canFormat(reportable: Reportable): Boolean
////
////    /**
////     * Formats the given [reportable] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
////     * of the given [parameterObject].
////     *
////     * This method should not be overridden (unfortunately an interface method cannot yet be final in Kotlin). This
////     * default implementation checks whether the given [reportable] is an [AssertionGroup] and calls
////     * [throwNotIntendedForGroups] in case it is; calls [formatSingle] with the given [reportable] and
////     * [parameterObject] otherwise.
////     *
////     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
////     * to format the given [reportable] without problems. If [canFormat] returns `false` then this method should throw
////     * an [UnsupportedOperationException].
////     * Moreover, it should throw an [UnsupportedOperationException] in case the [reportable] is an [AssertionGroup]
////     * -- use [Formatter.throwNotIntendedForGroups] for this purpose.
////     *
////     * @param reportable The assertion which should be formatted (not an [AssertionGroup]).
////     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
////     *   to which the result will be appended.
////     *
////     * @throws UnsupportedOperationException in case this [Formatter] cannot format the given [reportable]
////     *   ([canFormat] returns `false`) or if [reportable] is an [AssertionGroup].
////     */
////    fun format(reportable: Reportable, parameterObject: FormatterParameterObject) = when (reportable) {
////        is Group -> throwNotIntendedForGroups()
////        is Single -> formatSingle(reportable, parameterObject)
////        else -> throw UnsupportedOperationException("Reportable is neither a Group nor a Single, no idea how to format $reportable")
////    }
//
//    /**
//     * Formats the given [reportable] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
//     * of the given [parameterObject].
//     *
//     * The callee is responsible that not an [AssertionGroup] is passed to this function for which the outcome is
//     * unknown/unspecified. Call [formatGroup] to format an [AssertionGroup]. Call [format] in case you do not know
//     * what type [reportable] is.
//     *
//     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
//     * to format the given [reportable] without problems. If [canFormat] returns `false` then this method should throw
//     * an [UnsupportedOperationException].
//     *
//     * @param reportable The assertion which should be formatted (not an [AssertionGroup]).
//     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
//     *   to which the result will be appended.
//     *
//     * @throws UnsupportedOperationException in case this [Formatter] cannot format the given [reportable]
//     *   ([canFormat] returns `false`).
//     */
//    fun formatSingle(reportable: Single, parameterObject: FormatterParameterObject)
//
//    /**
//     * Formats the given [group] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
//     * of the given [parameterObject].
//     *
//     * Formatting an [AssertionGroup] makes up of two parts (where the first might be skipped):
//     *
//     * 1. formatting the group header (e.g. [AssertionGroup.description]: [AssertionGroup.representation])
//     * 2. formatting the [AssertionGroup.assertions] where the control flow for formatting should be steered
//     * by the [AssertionFormatterController] for which an [Formatter] has to call [formatChildren]
//     * and define a child-[AssertionFormatterParameterObject] which inter alia proposes the indent level to use, the
//     * prefix which should be for each assertion etc.
//     *
//     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
//     * to format the given [group] without problems. If [canFormat] returns `false` then this method should
//     * throw an [UnsupportedOperationException].
//     *
//     * @param group The assertion group which should be formatted.
//     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
//     *   to which the result will be appended.
//     * @param formatChildren The function which should be called to format the
//     *   [assertions][AssertionGroup.assertions] of the given [group].
//     *   It itself expects a [AssertionFormatterParameterObject] which is used for the child assertions and a function
//     *   which formats the child [Assertion]s in the context of the given [group].
//     */
//    fun formatGroup(
//        group: Group,
//        parameterObject: FormatterParameterObject,
//        formatChildren: (FormatterParameterObject, (Reportable) -> Unit) -> Unit
//    )
////
////    companion object {
////        private val CALL_FORMAT_GROUP = "do not use `${Formatter::format.name}` for " +
////            "`${AssertionGroup::class.simpleName}`s, " +
////            "use `${Formatter::formatGroup.name}` instead."
////
////        fun throwNotIntendedForGroups() {
////            throw UnsupportedOperationException(CALL_FORMAT_GROUP)
////        }
////    }
//}
