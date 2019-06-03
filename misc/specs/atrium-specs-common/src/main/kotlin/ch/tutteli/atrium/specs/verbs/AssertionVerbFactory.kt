package ch.tutteli.atrium.specs.verbs

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

interface AssertionVerbFactory {
    fun <T> checkImmediately(subject: T): Expect<T>
    fun checkException(act: () -> Unit): ThrowableThrown.Builder
}

