package ch.tutteli.atrium.robstoll.lib.creating

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.robstoll.lib.creating.throwable.thrown.builders.ThrowableThrownBuilder

fun _thrownBuilder(assertionVerb: Translatable, act: () -> Unit, reporter: Reporter): ThrowableThrown.Builder
    = ThrowableThrownBuilder(assertionVerb, act, reporter)
