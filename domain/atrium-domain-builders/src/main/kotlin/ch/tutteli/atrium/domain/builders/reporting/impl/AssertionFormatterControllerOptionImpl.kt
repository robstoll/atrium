package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterControllerOption
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

internal class AssertionFormatterControllerOptionImpl(
    override val objectFormatter: ObjectFormatter,
    override val translator: Translator
) : AssertionFormatterControllerOption {

    override fun withDefaultAssertionFormatterController()
        = AssertionFormatterFacadeOptionImpl(
        coreFactory.newAssertionFormatterController(),
        objectFormatter,
        translator
    )

    override fun withAssertionFormatterController(assertionFormatterController: AssertionFormatterController)
        = AssertionFormatterFacadeOptionImpl(
        assertionFormatterController,
        objectFormatter,
        translator
    )
}
