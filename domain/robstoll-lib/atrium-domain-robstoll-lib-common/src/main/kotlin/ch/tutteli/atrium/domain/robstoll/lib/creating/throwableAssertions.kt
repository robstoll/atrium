package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

fun _thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder =
    @Suppress("DEPRECATION")
    ThrowableThrownBuilder(assertionVerb, act, reporter)
