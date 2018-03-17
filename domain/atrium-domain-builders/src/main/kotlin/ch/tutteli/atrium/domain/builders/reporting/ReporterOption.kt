package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter

/**
 * Provides options to finalise the building process, which means creating a [Reporter].
 */
interface ReporterOption {

    val assertionFormatterFacade: AssertionFormatterFacade

    /**
     * Uses [CoreFactory.newOnlyFailureReporter] as [Reporter].
     */
    fun buildOnlyFailureReporter(): Reporter

    /**
     * Uses the given [factory] to build a custom [Reporter].
     */
    fun buildCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): Reporter
}

internal class ReporterOptionImpl(
    override val assertionFormatterFacade: AssertionFormatterFacade
) : ReporterOption {

    override fun buildOnlyFailureReporter(): Reporter
        = coreFactory.newOnlyFailureReporter(assertionFormatterFacade)

    override fun buildCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): Reporter
        = factory(assertionFormatterFacade)
}
