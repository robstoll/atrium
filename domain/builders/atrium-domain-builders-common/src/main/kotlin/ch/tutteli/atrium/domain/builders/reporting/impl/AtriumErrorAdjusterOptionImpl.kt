package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AtriumErrorAdjusterOption
import ch.tutteli.atrium.domain.builders.reporting.MultipleAdjustersOption
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilderFinalStep
import ch.tutteli.atrium.domain.builders.reporting.ReporterOption
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

internal class AtriumErrorAdjusterOptionImpl(
    override val assertionFormatterFacade: AssertionFormatterFacade
) : AtriumErrorAdjusterOption {

    override fun withNoOpAtriumErrorAdjuster(): ReporterOption =
        createReporterOption(coreFactory.newNoOpAtriumErrorAdjuster())

    override fun withRemoveAtriumFromAtriumErrorAdjuster(): ReporterOption =
        createReporterOption(coreFactory.newRemoveAtriumFromAtriumErrorAdjuster())

    override fun withRemoveRunnerAtriumErrorAdjuster(): ReporterOption =
        createReporterOption(coreFactory.newRemoveRunnerAtriumErrorAdjuster())

    override fun withAtriumErrorAdjuster(adjuster: AtriumErrorAdjuster): ReporterOption =
        createReporterOption(adjuster)

    override fun withMultipleAdjusters(configure: MultipleAdjustersOption.() -> Unit): ReporterOption {
        val adjusters = MultipleAdjustersOption.create()
            .apply(configure)
            .adjusters
        require(adjusters.size > 1) {
            "You need to define at least two adjusters, only ${adjusters.size} defined."
        }
        val first = adjusters.first()
        val tail = adjusters.drop(1)
        return createReporterOption(coreFactory.newMultiAtriumErrorAdjuster(first, tail.first(), tail.drop(1)))
    }

    private fun createReporterOption(atriumErrorAdjuster: AtriumErrorAdjuster) =
        ReporterOption.create(assertionFormatterFacade, atriumErrorAdjuster)


    override fun withOnlyFailureReporter(): ReporterBuilderFinalStep =
        withDefaultAtriumErrorAdjusters().withOnlyFailureReporter()

    override fun withCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): ReporterBuilderFinalStep =
        withNoOpAtriumErrorAdjuster().withCustomReporter { facade, _ -> factory(facade) }
}
