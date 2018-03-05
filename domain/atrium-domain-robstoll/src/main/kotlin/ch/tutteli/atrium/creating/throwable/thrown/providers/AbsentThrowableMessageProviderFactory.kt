package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [IAbsentThrowableMessageProviderFactory].
 */
object AbsentThrowableMessageProviderFactory : IAbsentThrowableMessageProviderFactory {

    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = _translatableBased(translatable)
}
