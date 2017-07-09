package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion

class AssertionFormatterFacade(private val assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade {

    override fun format(assertion: IAssertion, sb: StringBuilder, assertionFilter: (IAssertion) -> Boolean)
        = assertionFormatterController.format(assertion, AssertionFormatterMethodObject(sb, 0, assertionFilter))

    override fun register(assertionFormatterFactory: (IAssertionFormatterController) -> IAssertionFormatter)
        = assertionFormatterController.register(assertionFormatterFactory(assertionFormatterController))
}
