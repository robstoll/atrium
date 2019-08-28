package ch.tutteli.atrium.spec

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

@Deprecated("Will be removed with 1.0.0")
interface AssertionVerbFactory {
    fun <T : Any> checkImmediately(subject: T): AssertionPlant<T>
    fun <T : Any> checkLazily(subject: T, assertionCreator: Assert<T>.() -> Unit): AssertionPlant<T>
    fun <T : Any?> checkNullable(subject: T): AssertionPlantNullable<T>
    fun checkException(act: () -> Unit): ThrowableThrown.Builder
}

