package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilderFinalStep
import ch.tutteli.atrium.domain.builders.reporting.ReporterOption
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter

internal class ReporterOptionImpl(
    override val assertionFormatterFacade: AssertionFormatterFacade
) : ReporterOption {

    override fun onlyFailureReporter(): ReporterBuilderFinalStep
        = customReporter(coreFactory::newOnlyFailureReporter)

    override fun customReporter(factory: (AssertionFormatterFacade) -> Reporter): ReporterBuilderFinalStep
        = ReporterBuilderFinalStep.create({factory(assertionFormatterFacade)})
}
