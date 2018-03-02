package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

fun _thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
    = ThrowableThrownBuilder(assertionVerb, act, reporter)
