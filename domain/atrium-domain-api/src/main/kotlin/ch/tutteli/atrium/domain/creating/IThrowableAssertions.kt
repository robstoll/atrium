package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import java.util.*

/**
 * The access point to an implementation of [ThrowableAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val throwableAssertions by lazy { SingleServiceLoader.load(ThrowableAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Throwable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ThrowableAssertions {
    fun thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
}

