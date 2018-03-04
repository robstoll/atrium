package ch.tutteli.atrium.creating.throwable.thrown.providers

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

fun _translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
    = AbsentThrowableMessageProvider(translatable)
