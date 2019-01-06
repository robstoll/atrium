@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.throwable.thrown.providers

import ch.tutteli.atrium.assertions.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a [ThrowableThrown.AbsentThrowableMessageProvider] which is using a given [Translatable] which in $
 * turn explains an absent [Throwable].
 */
@Deprecated("Use the provider from package creating; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.throwable.thrown.providers.TranslatableAsAbsentThrowableMessageProvider"))
class TranslatableAsAbsentThrowableMessageProvider(translatable: Translatable) : ThrowableThrown.AbsentThrowableMessageProvider {
    override val message = RawString.create(translatable)
}
