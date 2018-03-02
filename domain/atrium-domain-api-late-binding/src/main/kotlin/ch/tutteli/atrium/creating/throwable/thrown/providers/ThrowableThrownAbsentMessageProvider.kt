package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwUnsupportedOperationException
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A dummy implementation of [IThrowableThrownAbsentMessageProvider] which should be replaced by an actual implementation.
 */
object ThrowableThrownAbsentMessageProvider : IThrowableThrownAbsentMessageProvider {
    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = throwUnsupportedOperationException()
}
