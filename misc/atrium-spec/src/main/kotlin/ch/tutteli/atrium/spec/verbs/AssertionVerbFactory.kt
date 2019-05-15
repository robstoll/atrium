package ch.tutteli.atrium.spec.verbs

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

interface AssertionVerbFactory {
    fun <T> checkImmediately(subject: T): Expect<T>
    fun <T> checkLazily(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T>
    fun checkException(act: () -> Unit): ThrowableThrown.Builder
}

