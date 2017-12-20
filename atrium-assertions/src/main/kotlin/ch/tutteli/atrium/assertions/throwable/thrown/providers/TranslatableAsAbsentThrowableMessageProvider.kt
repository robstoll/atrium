package ch.tutteli.atrium.assertions.throwable.thrown.providers

import ch.tutteli.atrium.assertions.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Represents an [ThrowableThrown.AbsentThrowableMessageProvider] which is using a given [ITranslatable] which in $
 * turn explains an absent [Throwable].
 */
class TranslatableAsAbsentThrowableMessageProvider(translatable: ITranslatable) : ThrowableThrown.AbsentThrowableMessageProvider {
    override val message = TranslatableRawString(translatable)
}
