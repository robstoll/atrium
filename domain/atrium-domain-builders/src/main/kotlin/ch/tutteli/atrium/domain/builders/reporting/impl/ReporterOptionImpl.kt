package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.ReporterOption
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter

internal class ReporterOptionImpl(
    override val assertionFormatterFacade: AssertionFormatterFacade
) : ReporterOption {

    override fun buildOnlyFailureReporter(): Reporter
        = coreFactory.newOnlyFailureReporter(assertionFormatterFacade)

    override fun buildCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): Reporter
        = factory(assertionFormatterFacade)
}
