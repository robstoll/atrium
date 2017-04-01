package ch.tutteli.atrium.verbs.assert

import ch.tutteli.atrium.creating.AssertionPlantFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent

fun <T : Any> assert(subject: T): IAssertionPlant<T>
    = AssertionPlantFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionPlantNullable<T>
    = AssertionPlantFactory.newNullable("assert", subject)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AssertionPlantFactory.newCheckLazilyAtTheEnd("assert", subject, createAssertions)

fun assert(act: () -> Unit): ThrowableFluent
    = AssertionPlantFactory.throwableFluent("expect the thrown exception", act)
