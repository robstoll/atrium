package ch.tutteli.atrium.robstoll.lib.creating.throwable.thrown.providers

import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

fun _translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
    = AbsentThrowableMessageProvider(translatable)
