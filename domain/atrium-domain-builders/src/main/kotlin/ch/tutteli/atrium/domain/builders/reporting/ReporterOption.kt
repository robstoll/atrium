package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.ReporterOptionImpl
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
    fun onlyFailureReporter(): ReporterBuilderFinalStep

    /**
     * Uses the given [factory] to build a custom [Reporter].
     */
    fun customReporter(factory: (AssertionFormatterFacade) -> Reporter): ReporterBuilderFinalStep

    companion object {
        fun create(assertionFormatterFacade: AssertionFormatterFacade): ReporterOption
            = ReporterOptionImpl(assertionFormatterFacade)
    }
}
