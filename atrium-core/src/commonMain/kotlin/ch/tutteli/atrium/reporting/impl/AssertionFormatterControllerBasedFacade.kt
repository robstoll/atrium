//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject

/**
 * Responsible to call an appropriate [AssertionFormatter] which supports [format]ing a given [Assertion] -- it does
 * so by delegating this responsibility to the specified [assertionFormatterController].
 *
 * @constructor Responsible to call an appropriate [AssertionFormatter] which supports [format]ing a given [Assertion] -- it does
 *   so by delegating this responsibility to the given [assertionFormatterController].
 * @param assertionFormatterController The controller used to control the flow of formatting.
 */
@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
class AssertionFormatterControllerBasedFacade(private val assertionFormatterController: AssertionFormatterController) :
    AssertionFormatterFacade {

    override fun format(assertion: Assertion, sb: StringBuilder, assertionFilter: (Assertion) -> Boolean) =
        assertionFormatterController.format(assertion, AssertionFormatterParameterObject.new(sb, assertionFilter))

    override fun register(assertionFormatterFactory: (AssertionFormatterController) -> AssertionFormatter) =
        assertionFormatterController.register(assertionFormatterFactory(assertionFormatterController))
}
