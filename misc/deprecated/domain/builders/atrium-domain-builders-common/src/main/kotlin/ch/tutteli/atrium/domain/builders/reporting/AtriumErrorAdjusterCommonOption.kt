//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

/**
 * Provides common options to create an [AtriumErrorAdjuster].
 */
@Deprecated("Configure components via withOptions when creating an expectation verb instead; will be removed with 0.17.0")
interface AtriumErrorAdjusterCommonOption<R : Any> {

    /**
     * Uses an [AtriumErrorAdjuster] which removes stackBacktrace frames of test runners from a given [AtriumError].
     */
    fun withRemoveRunnerAtriumErrorAdjuster(): R

    /**
     * Uses an [AtriumErrorAdjuster] which removes stackBacktrace frames of Atrium from a given [AtriumError].
     */
    fun withRemoveAtriumFromAtriumErrorAdjuster(): R

    /**
     * Uses the given [AtriumErrorAdjuster] as custom [AtriumErrorAdjuster].
     */
    fun withAtriumErrorAdjuster(adjuster: AtriumErrorAdjuster): R
}
