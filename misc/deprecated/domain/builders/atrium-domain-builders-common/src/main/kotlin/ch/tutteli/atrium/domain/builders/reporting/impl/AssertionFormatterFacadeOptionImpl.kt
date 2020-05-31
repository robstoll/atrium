package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterFacadeOption
import ch.tutteli.atrium.domain.builders.reporting.AssertionPairFormatterOption
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

internal class AssertionFormatterFacadeOptionImpl(
    override val assertionFormatterController: AssertionFormatterController,
    override val objectFormatter: ObjectFormatter,
    override val translator: Translator
) : AssertionFormatterFacadeOption {

    override fun withDefaultAssertionFormatterFacade() =
        withAssertionFormatterFacade(coreFactory::newAssertionFormatterFacade)

    override fun withAssertionFormatterFacade(factory: (AssertionFormatterController) -> AssertionFormatterFacade) =
        AssertionPairFormatterOption.create(factory(assertionFormatterController), objectFormatter, translator)
}
