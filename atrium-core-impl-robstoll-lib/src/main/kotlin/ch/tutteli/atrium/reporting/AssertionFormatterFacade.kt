package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Responsible to call an appropriate [IAssertionFormatter] which supports [format]ing a given [IAssertion].
 *
 * @constructor Responsible to call an appropriate [IAssertionFormatter] which supports [format]ing
 *              a given [IAssertion].
 * @param assertionFormatterController The controller used to control the flow of formatting.
 */
class AssertionFormatterFacade(private val assertionFormatterController: IAssertionFormatterController) : IAssertionFormatterFacade {

    override fun format(assertion: IAssertion, sb: StringBuilder, assertionFilter: (IAssertion) -> Boolean)
        = assertionFormatterController.format(assertion, AssertionFormatterMethodObject.new(sb, assertionFilter))

    override fun register(assertionFormatterFactory: (IAssertionFormatterController) -> IAssertionFormatter)
        = assertionFormatterController.register(assertionFormatterFactory(assertionFormatterController))
}
