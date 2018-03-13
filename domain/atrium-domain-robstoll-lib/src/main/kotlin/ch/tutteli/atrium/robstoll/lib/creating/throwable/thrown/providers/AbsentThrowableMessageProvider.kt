package ch.tutteli.atrium.robstoll.lib.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a [ThrowableThrown.AbsentThrowableMessageProvider] which is using a given [Translatable] which in $
 * turn explains an absent [Throwable].
 */
class AbsentThrowableMessageProvider(translatable: Translatable) : ThrowableThrown.AbsentThrowableMessageProvider {
    override val message = RawString.create(translatable)
}
