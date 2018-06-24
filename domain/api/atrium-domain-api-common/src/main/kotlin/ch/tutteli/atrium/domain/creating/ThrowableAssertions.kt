package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [ThrowableAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val throwableAssertions by lazy { loadSingleService(ThrowableAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Throwable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ThrowableAssertions {
    fun thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
}

