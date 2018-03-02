package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A dummy implementation of [IThrowableAssertions] which should be replaced by an actual implementation.
 */
object ThrowableAssertions: IThrowableAssertions {
    override fun thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
        = _thrownBuilder(assertionVerb, act, reporter)
}

