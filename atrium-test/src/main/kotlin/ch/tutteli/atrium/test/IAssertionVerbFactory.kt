package ch.tutteli.atrium.test

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent

interface IAssertionVerbFactory {
    fun <T : Any> checkImmediately(subject: T): IAssertionPlant<T>
    fun <T : Any> checkLazily(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit) : IAssertionPlant<T>
    fun <T : Any?> checkNullable(subject: T): IAssertionPlantNullable<T>
    fun checkException(act: () -> Unit): ThrowableFluent
}

