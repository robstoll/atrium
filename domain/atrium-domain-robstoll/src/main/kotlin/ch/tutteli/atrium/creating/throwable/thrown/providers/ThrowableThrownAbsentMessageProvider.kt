package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [IThrowableThrownAbsentMessageProvider].
 */
object ThrowableThrownAbsentMessageProvider : IThrowableThrownAbsentMessageProvider {
    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = _translatableBased(translatable)
}
