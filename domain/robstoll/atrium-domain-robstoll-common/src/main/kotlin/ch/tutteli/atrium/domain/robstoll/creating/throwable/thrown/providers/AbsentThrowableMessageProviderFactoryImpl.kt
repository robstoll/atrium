@file:Suppress("DEPRECATION" /* will be remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.providers

import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.providers._translatableBased
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("Will be removed with 1.0.0")
class AbsentThrowableMessageProviderFactoryImpl : AbsentThrowableMessageProviderFactory {

    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider =
        _translatableBased(translatable)
}
