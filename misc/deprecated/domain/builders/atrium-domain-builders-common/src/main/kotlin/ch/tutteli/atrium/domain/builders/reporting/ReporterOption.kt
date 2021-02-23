//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.ReporterOptionImpl
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

/**
 * Provides options to finalise the building process, which means creating a [Reporter].
 */
@Deprecated("Configure components via withOptions when creating an expectation verb instead; will be removed with 0.17.0")
interface ReporterOption {

    /**
     * The previously chosen [AssertionFormatterFacade].
     */
    val assertionFormatterFacade: AssertionFormatterFacade

    /**
     * The previously chosen [AtriumErrorAdjuster].
     */
    val atriumErrorAdjuster: AtriumErrorAdjuster

    /**
     * Uses [CoreFactory.newOnlyFailureReporter] as [Reporter].
     */
    fun withOnlyFailureReporter(): ReporterBuilderFinalStep

    /**
     * Uses the given [factory] to build a custom [Reporter].
     */
    fun withCustomReporter(
        factory: (AssertionFormatterFacade, AtriumErrorAdjuster) -> Reporter
    ): ReporterBuilderFinalStep

    companion object {
        fun create(
            assertionFormatterFacade: AssertionFormatterFacade,
            atriumErrorAdjuster: AtriumErrorAdjuster
        ): ReporterOption = ReporterOptionImpl(assertionFormatterFacade, atriumErrorAdjuster)
    }
}
