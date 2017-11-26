package ch.tutteli.atrium.assertions.throwable.thrown.providers

import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Represents an [IThrowableThrown.IAbsentThrowableMessageProvider] which is using a given [ITranslatable] which in $
 * turn explains an absent [Throwable].
 */
class TranslatableAsAbsentThrowableMessageProvider(translatable: ITranslatable) : IThrowableThrown.IAbsentThrowableMessageProvider {
    override val message = TranslatableRawString(translatable)
}
