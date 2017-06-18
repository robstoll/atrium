package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.Message

/**
 * Responsible to control the flow of reporting using [register]ed [IAssertionFormatter]s.
 */
interface IAssertionFormatterController {

    /**
     * Finds a suitable [IAssertionFormatter] -- which was previously [register]ed -- for the given [assertion] and
     * formats it.
     *
     * The [methodObject] allows to define an [assertionFilter][AssertionFormatterMethodObject.assertionFilter] and
     * an [messageFilter][AssertionFormatterMethodObject.messageFilter] to filter out [IAssertion]s, [Message]s
     * respectively (for instance, filter out messages which hold --> see [IAtriumFactory.newOnlyFailureReporter]).
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

    companion object{
        fun noSuitableAssertionFormatterFound(assertion: IAssertion) : Nothing = throw UnsupportedOperationException(
            "no suitable ${IAssertionFormatter::class.simpleName} found for the given assertion: $assertion")
    }
}
