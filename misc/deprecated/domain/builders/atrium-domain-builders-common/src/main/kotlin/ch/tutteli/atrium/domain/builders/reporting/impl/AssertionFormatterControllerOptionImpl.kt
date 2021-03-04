//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterControllerOption
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterFacadeOption
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

@Deprecated("Will be removed with 0.17.0")
internal class AssertionFormatterControllerOptionImpl(
    override val objectFormatter: ObjectFormatter,
    override val translator: Translator
) : AssertionFormatterControllerOption {

    override fun withDefaultAssertionFormatterController() =
        withAssertionFormatterController(coreFactory.newAssertionFormatterController())

    override fun withAssertionFormatterController(assertionFormatterController: AssertionFormatterController) =
        AssertionFormatterFacadeOption.create(assertionFormatterController, objectFormatter, translator)
}
