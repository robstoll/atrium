package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.domain.creating.ThrowableAssertions
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating._thrownBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

class ThrowableAssertionsImpl : ThrowableAssertions {

    override fun thrownBuilder(
        assertionVerb: Translatable,
        act: () -> Unit,
        reporter: Reporter
    ): ThrowableThrown.Builder = _thrownBuilder(assertionVerb, act, reporter)
}

