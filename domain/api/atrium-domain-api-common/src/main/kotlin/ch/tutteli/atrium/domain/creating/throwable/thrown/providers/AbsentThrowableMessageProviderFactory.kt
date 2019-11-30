@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.domain.creating.throwable.thrown.providers

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [AbsentThrowableMessageProviderFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Suppress("DEPRECATION")
@Deprecated("Will be removed with 1.0.0")
val absentThrowableMessageProviderFactory by lazy { loadSingleService(AbsentThrowableMessageProviderFactory::class) }


/**
 * Defines the minimum set of [ThrowableThrown.AbsentThrowableMessageProvider]s,
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated("Will be removed with 1.0.0")
interface AbsentThrowableMessageProviderFactory {

    @Suppress("DEPRECATION")
    fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
}
