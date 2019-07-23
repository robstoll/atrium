package ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.providers

import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.providers._translatableBased
import ch.tutteli.atrium.reporting.translating.Translatable


class AbsentThrowableMessageProviderFactoryImpl : AbsentThrowableMessageProviderFactory {

    override fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = _translatableBased(translatable)
}
