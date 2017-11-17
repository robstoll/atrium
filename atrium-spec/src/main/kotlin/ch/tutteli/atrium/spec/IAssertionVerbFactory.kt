package ch.tutteli.atrium.spec

import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

interface IAssertionVerbFactory {
    fun <T : Any> checkImmediately(subject: T): IAssertionPlant<T>
    fun <T : Any> checkLazily(subject: T, assertionCreator: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T>
    fun <T : Any?> checkNullable(subject: T): IAssertionPlantNullable<T>
    fun checkException(act: () -> Unit): ThrowableThrownBuilder
}

