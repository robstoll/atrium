package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the minimum set of [ThrowableThrown.AbsentThrowableMessageProvider]s,
 * which an implementation of the domain of Atrium has to provide.
 */
interface IThrowableThrownAbsentMessageProvider {
    fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
}
