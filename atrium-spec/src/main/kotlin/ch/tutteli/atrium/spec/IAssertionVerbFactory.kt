package ch.tutteli.atrium.spec

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IThrowableFluent

interface IAssertionVerbFactory {
    fun <T : Any> checkImmediately(subject: T): IAssertionPlant<T>
    fun <T : Any> checkLazily(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T>
    fun <T : Any?> checkNullable(subject: T): IAssertionPlantNullable<T>
    fun checkException(act: () -> Unit): IThrowableFluent
}

