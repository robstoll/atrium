package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwUnsupportedOperationException
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A dummy implementation of [IAbsentThrowableMessageProviderFactory] which should be replaced by an actual implementation.
 */
object AbsentThrowableMessageProviderFactory : IAbsentThrowableMessageProviderFactory {

    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = throwUnsupportedOperationException()
}
