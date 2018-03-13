package ch.tutteli.atrium.robstoll.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.robstoll.lib.creating.throwable.thrown.providers._translatableBased

/**
 * Robstoll's implementation of [AbsentThrowableMessageProviderFactory].
 */
class AbsentThrowableMessageProviderFactoryImpl : AbsentThrowableMessageProviderFactory {

    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = _translatableBased(translatable)
}
