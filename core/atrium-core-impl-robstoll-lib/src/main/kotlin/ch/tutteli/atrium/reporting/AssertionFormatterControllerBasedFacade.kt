package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion

/**
 * Responsible to call an appropriate [AssertionFormatter] which supports [format]ing a given [Assertion] -- it does
 * so by delegating this responsibility to the specified [assertionFormatterController].
 *
 * @constructor Responsible to call an appropriate [AssertionFormatter] which supports [format]ing a given [Assertion] -- it does
 *   so by delegating this responsibility to the given [assertionFormatterController].
 * @param assertionFormatterController The controller used to control the flow of formatting.
 */
class AssertionFormatterControllerBasedFacade(private val assertionFormatterController: AssertionFormatterController) : AssertionFormatterFacade {

    override fun format(assertion: Assertion, sb: StringBuilder, assertionFilter: (Assertion) -> Boolean)
        = assertionFormatterController.format(assertion, AssertionFormatterMethodObject.new(sb, assertionFilter))

    override fun register(assertionFormatterFactory: (AssertionFormatterController) -> AssertionFormatter)
        = assertionFormatterController.register(assertionFormatterFactory(assertionFormatterController))
}
