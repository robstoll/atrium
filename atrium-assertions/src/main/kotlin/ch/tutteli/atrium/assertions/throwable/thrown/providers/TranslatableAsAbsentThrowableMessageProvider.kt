package ch.tutteli.atrium.assertions.throwable.thrown.providers

import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Uses an [ITranslatable] as message which should explain an absent [Throwable].
 */
class TranslatableAsAbsentThrowableMessageProvider(translatable: ITranslatable) : IThrowableThrown.IAbsentThrowableMessageProvider {
    override val message = TranslatableRawString(translatable)
}
