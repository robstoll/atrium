//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.domain.builders.reporting.impl.ReporterBuilderFinalStepImpl
import ch.tutteli.atrium.reporting.Reporter

/**
 * Final step in the [ReporterBuilder] process, creates the desired [Reporter].
 */
@Deprecated("Configure components via withOptions when creating an expectation verb instead; will be removed with 0.17.0")
interface ReporterBuilderFinalStep {

    /**
     * Creates and returns the new [Reporter].
     */
    fun build(): Reporter

    companion object {
        fun create(factory: () -> Reporter): ReporterBuilderFinalStep = ReporterBuilderFinalStepImpl(factory)
    }
}
