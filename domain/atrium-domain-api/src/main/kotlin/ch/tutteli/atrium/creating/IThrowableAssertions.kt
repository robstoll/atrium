package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the minimum set of assertion functions and builders applicable to [Throwable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IThrowableAssertions {
    fun thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
}

