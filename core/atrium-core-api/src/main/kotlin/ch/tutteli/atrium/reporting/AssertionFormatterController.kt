package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType

/**
 * Responsible to control the flow of reporting using [register]ed [AssertionFormatter]s.
 */
interface AssertionFormatterController {

    /**
     * Finds a suitable [AssertionFormatter] -- which was previously [register]ed -- for the given [assertion] and
     * formats it.
     *
     * The [methodObject] allows to define an [assertionFilter][AssertionFormatterMethodObject.assertionFilter]
     * to filter out [IAssertion]s (for instance, filter out messages which hold
     * &rarr; see [IAtriumFactory.newOnlyFailureReporter]).
     * Moreover the controller should take into account whether the control flow
     * [AssertionFormatterMethodObject.isNotInDoNotFilterGroup] or is in such group, in which case the filtering should
     * not apply.
     *
     * @param assertion The assertion which shall be formatted.
     * @param methodObject Used to share data between this [AssertionFormatterController] and the [register]ed
     *        [IAssertionFormatter]s.
     *
     * @throws UnsupportedOperationException if no suitable [AssertionFormatter] is found.
     *
     * @see AssertionFormatterMethodObject
     */
    fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject)

    /**
     * Registers the given [assertionFormatter], which means it will be considered when an [IAssertion]
     * shall be [format]ted.
     *
     * @param assertionFormatter The [AssertionFormatter] which shall be considered when [format] is called.
     */
    fun register(assertionFormatter: AssertionFormatter)

    /**
     * Checks whether the given [assertion] is an [IAssertionGroup] and if its [type][IAssertionGroup.type]
     * is a [IExplanatoryAssertionGroupType].
     *
     * @return `true` if it is an explanatory assertion group; `false` otherwise.
     */
    fun isExplanatoryAssertionGroup(assertion: IAssertion)
        = (assertion is IAssertionGroup && assertion.type is IExplanatoryAssertionGroupType)

    companion object {
        /**
         * Throws an [UnsupportedOperationException] stating that no suitable [AssertionFormatter] was found for the
         * given [assertion].
         *
         * @throws UnsupportedOperationException stating that no suitable [AssertionFormatter] was found for the
         * given [assertion].
         */
        fun noSuitableAssertionFormatterFound(assertion: IAssertion): Nothing = throw UnsupportedOperationException(
            "no suitable ${AssertionFormatter::class.simpleName} found for the given assertion: $assertion")
    }
}
