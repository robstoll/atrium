//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilderFinalStep
import ch.tutteli.atrium.domain.builders.reporting.ReporterOption
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

@Deprecated("Will be removed with 0.17.0")
internal class ReporterOptionImpl(
    override val assertionFormatterFacade: AssertionFormatterFacade,
    override val atriumErrorAdjuster: AtriumErrorAdjuster
) : ReporterOption {

    override fun withOnlyFailureReporter(): ReporterBuilderFinalStep =
        withCustomReporter(coreFactory::newOnlyFailureReporter)

    override fun withCustomReporter(
        factory: (AssertionFormatterFacade, AtriumErrorAdjuster) -> Reporter
    ): ReporterBuilderFinalStep = ReporterBuilderFinalStep.create {
        factory(assertionFormatterFacade, atriumErrorAdjuster)
    }
}
