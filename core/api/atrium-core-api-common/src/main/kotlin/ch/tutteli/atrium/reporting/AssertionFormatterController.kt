package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.CoreFactory

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
     * [AssertionFormatterParameterObject.isNotInDoNotFilterGroup] or is in such a group,
     * in which case the filtering should not apply.
     *
     * Last but not least, an [AssertionFormatterController] has to take care of [AssertionGroup] with an
     * [InvisibleAssertionGroupType] as its [AssertionGroup.type]. Such groups should not be format as group but instead
     * each [AssertionGroup.assertions] should be formatted. This also means, that if there are nested assertion groups
     * with an [InvisibleAssertionGroupType], that their [AssertionGroup.assertions] should be formatted as if they
     * were all added directly in the surrounding assertion group.
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
     * is an [ExplanatoryAssertionGroupType].
     *
     * @return `true` if it is an explanatory assertion group; `false` otherwise.
     */
    fun isExplanatoryAssertionGroup(assertion: Assertion)
        = assertion is AssertionGroup && assertion.type is ExplanatoryAssertionGroupType

    companion object {
        /**
         * Throws an [UnsupportedOperationException] stating that no suitable [AssertionFormatter] was found for the
         * given [assertion].
         *
         * @throws UnsupportedOperationException stating that no suitable [AssertionFormatter] was found for the
         *   given [assertion].
         */
        fun noSuitableAssertionFormatterFound(assertion: Assertion): Nothing = throw UnsupportedOperationException(
            "No suitable ${AssertionFormatter::class.simpleName} found for the given assertion: $assertion"
        )
    }
}
