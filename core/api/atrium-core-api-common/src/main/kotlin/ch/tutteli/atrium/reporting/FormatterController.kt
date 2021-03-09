//package ch.tutteli.atrium.reporting
//
//import ch.tutteli.atrium.assertions.Assertion
//import ch.tutteli.atrium.assertions.AssertionGroup
//import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
//import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
//import ch.tutteli.atrium.core.CoreFactory
//
///**
// * Responsible to control the flow of reporting [Reportable]s.
// */
//interface FormatterController {
//
//    /**
//     * Finds a suitable [Formatter] for the given [reportable] and formats it.
//     *
//     * The [parameterObject] allows to define a [filter][FormatterParameterObject.filter]
//     * to filter out [Assertion]s (for instance, filter out [Proof]s which [hold][Proof.hold]
//     * &rarr; see [CoreFactory.newOnlyFailureReporter]).
//     * Moreover the controller should take into account whether the control flow
//     * [FormatterParameterObject.isNotInDoNotFilterGroup] or is in such a group,
//     * in which case the filtering should not apply.
//     *
//     * Last but not least, an [FormatterController] has to take care of [AssertionGroup] with an
//     * [InvisibleAssertionGroupType] as its [AssertionGroup.type]. Such groups should not be format as group but instead
//     * each [AssertionGroup.assertions] should be formatted. This also means, that if there are nested assertion groups
//     * with an [InvisibleAssertionGroupType], that their [AssertionGroup.assertions] should be formatted as if they
//     * were all added directly in the surrounding assertion group.
//     *
//     * @param reportable The assertion which shall be formatted.
//     * @param parameterObject Used to share data between this [FormatterController] and [Formatter]s.
//     *
//     * @throws UnsupportedOperationException if no suitable [Formatter] is found.
//     *
//     * @see FormatterParameterObject
//     */
//    fun format(reportable: Reportable, parameterObject: FormatterParameterObject)
//
//    /**
//     * Checks whether the given [reportable] is a [Group] and if its [type][Group.type]
//     * is an [ExplanatoryAssertionGroupType].
//     *
//     * @return `true` if it is an explanatory assertion group; `false` otherwise.
//     */
//    fun isExplanatoryAssertionGroup(reportable: Assertion) =
//        reportable is Group && reportable.type is ExplanatoryAssertionGroupType
//
//    companion object {
//        /**
//         * Throws an [UnsupportedOperationException] stating that no suitable [Formatter] was found for the
//         * given [reportable].
//         *
//         * @throws UnsupportedOperationException stating that no suitable [Formatter] was found for the
//         *   given [reportable].
//         */
//        fun noSuitableFormatterFound(reportable: Reportable): Nothing = throw UnsupportedOperationException(
//            "No suitable ${Formatter::class.simpleName} found for the given assertion: $reportable"
//        )
//    }
//}
