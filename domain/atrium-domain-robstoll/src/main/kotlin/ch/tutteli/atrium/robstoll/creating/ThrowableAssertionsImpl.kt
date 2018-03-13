package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.ThrowableAssertions
import ch.tutteli.atrium.creating._thrownBuilder
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [ThrowableAssertions].
 */
class ThrowableAssertionsImpl: ThrowableAssertions {

    override fun thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
        = _thrownBuilder(assertionVerb, act, reporter)
}

