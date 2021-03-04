//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilderFinalStep
import ch.tutteli.atrium.reporting.Reporter

@Deprecated("Will be removed with 0.17.0")
class ReporterBuilderFinalStepImpl(private val factory: () -> Reporter) : ReporterBuilderFinalStep {
    override fun build(): Reporter = factory()
}
