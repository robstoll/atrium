package ch.tutteli.atrium.spec

import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

interface AssertionVerbFactory {
    fun <T : Any> checkImmediately(subject: T): AssertionPlant<T>
    fun <T : Any> checkLazily(subject: T, assertionCreator: Assert<T>.() -> Unit): AssertionPlant<T>
    fun <T : Any?> checkNullable(subject: T): AssertionPlantNullable<T>
    fun checkException(act: () -> Unit): ThrowableThrownBuilder
}

