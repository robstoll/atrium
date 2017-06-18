package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.Message

internal class AssertionFormatterFacade(private val assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade {

    override fun format(assertion: IAssertion, sb: StringBuilder, assertionFilter: (IAssertion) -> Boolean, messageFilter: (Message) -> Boolean)
        = assertionFormatterController.format(assertion, AssertionFormatterMethodObject(sb, 0, assertionFilter, messageFilter))

    override fun register(assertionFormatterFactory: (IAssertionFormatterController) -> IAssertionFormatter)
        = assertionFormatterController.register(assertionFormatterFactory(assertionFormatterController))
}
