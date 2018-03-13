package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType

/**
 * Responsible to control the flow of reporting using [register]ed [AssertionFormatter]s.
 */
interface AssertionFormatterController {

    /**
     * Finds a suitable [AssertionFormatter] -- which was previously [register]ed -- for the given [assertion] and
     * formats it.
     *
     * The [parameterObject] allows to define an [assertionFilter][AssertionFormatterParameterObject.assertionFilter]
     * to filter out [Assertion]s (for instance, filter out messages which hold
     * &rarr; see [CoreFactory.newOnlyFailureReporter]).
     * Moreover the controller should take into account whether the control flow
     * [AssertionFormatterParameterObject.isNotInDoNotFilterGroup] or is in such group, in which case the filtering should
     * not apply.
     *
     * @param assertion The assertion which shall be formatted.
     * @param parameterObject Used to share data between this [AssertionFormatterController] and the [register]ed
     *   [AssertionFormatter]s.
     *
     * @throws UnsupportedOperationException if no suitable [AssertionFormatter] is found.
     *
     * @see AssertionFormatterParameterObject
     */
    fun format(assertion: Assertion, parameterObject: AssertionFormatterParameterObject)

    /**
     * Registers the given [assertionFormatter], which means it will be considered when an [Assertion]
     * shall be [format]ted.
     *
     * @param assertionFormatter The [AssertionFormatter] which shall be considered when [format] is called.
     */
    fun register(assertionFormatter: AssertionFormatter)

    /**
     * Checks whether the given [assertion] is an [AssertionGroup] and if its [type][AssertionGroup.type]
     * is a [ExplanatoryAssertionGroupType].
     *
     * @return `true` if it is an explanatory assertion group; `false` otherwise.
     */
    fun isExplanatoryAssertionGroup(assertion: Assertion)
        = (assertion is AssertionGroup && assertion.type is ExplanatoryAssertionGroupType)

    companion object {
        /**
         * Throws an [UnsupportedOperationException] stating that no suitable [AssertionFormatter] was found for the
         * given [assertion].
         *
         * @throws UnsupportedOperationException stating that no suitable [AssertionFormatter] was found for the
         *   given [assertion].
         */
        fun noSuitableAssertionFormatterFound(assertion: Assertion): Nothing = throw UnsupportedOperationException(
            "no suitable ${AssertionFormatter::class.simpleName} found for the given assertion: $assertion")
    }
}
