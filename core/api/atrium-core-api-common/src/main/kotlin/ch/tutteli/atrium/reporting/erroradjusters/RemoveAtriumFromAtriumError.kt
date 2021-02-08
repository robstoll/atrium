package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.creating.ComponentFactoryContainer

/**
 * Responsible to remove the stacktrace of Atrium from an [AtriumError].
 *
 * It is a marker interface so that one can [ComponentFactoryContainer.buildOrNull] an implementation of
 * a [AtriumErrorAdjuster] with the desired behaviour.
 */
interface RemoveAtriumFromAtriumError : AtriumErrorAdjuster
