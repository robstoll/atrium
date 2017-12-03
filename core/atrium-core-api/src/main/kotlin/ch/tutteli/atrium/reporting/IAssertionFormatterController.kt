package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType

/**
 * Responsible to control the flow of reporting using [register]ed [IAssertionFormatter]s.
 */
interface IAssertionFormatterController {

    /**
     * Finds a suitable [IAssertionFormatter] -- which was previously [register]ed -- for the given [assertion] and
     * formats it.
     *
     * The [methodObject] allows to define an [assertionFilter][AssertionFormatterMethodObject.assertionFilter]
     * to filter out [IAssertion]s (for instance, filter out messages which hold
     * &rarr; see [IAtriumFactory.newOnlyFailureReporter]).
     *
     * @param assertion The assertion which shall be formatted.
     * @param methodObject Used to share data between this [IAssertionFormatterController] and the [register]ed
     *        [IAssertionFormatter]s.
     *
     * @throws UnsupportedOperationException if no suitable [IAssertionFormatter] is found.
     *
     * @see AssertionFormatterMethodObject
     */
    fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject)

    /**
     * Registers the given [assertionFormatter], which means it will be considered when an [IAssertion]
     * shall be [format]ted.
     *
     * @param assertionFormatter The [IAssertionFormatter] which shall be considered when [format] is called.
     */
    fun register(assertionFormatter: IAssertionFormatter)

    /**
     * Checks whether the given [assertion] is an [IAssertionGroup] and if its [type][IAssertionGroup.type]
     * is a [IExplanatoryAssertionGroupType].
     *
     * @return `true` if it is an explanatory assertion group; `false` otherwise.
     */
    fun isExplanatoryAssertionGroup(assertion: IAssertion)
        = (assertion is IAssertionGroup && assertion.type is IExplanatoryAssertionGroupType)

    companion object {
        fun noSuitableAssertionFormatterFound(assertion: IAssertion): Nothing = throw UnsupportedOperationException(
            "no suitable ${IAssertionFormatter::class.simpleName} found for the given assertion: $assertion")
    }
}
