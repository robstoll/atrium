package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.domain.creating.ThrowableAssertions
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

//TODO remove with 1.0.0 if there aren't any non-deprecated functions added
class ThrowableAssertionsImpl : ThrowableAssertions {

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun thrownBuilder(
        assertionVerb: Translatable,
        act: () -> Unit,
        reporter: Reporter
    ): ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder =
        ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.builders.ThrowableThrownBuilder(
            assertionVerb, act, reporter
        )
}

