package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.creating.*

fun <T : Any> expect(subject: T): IAssertionPlant<T>
    = AssertionPlantFactory.newCheckImmediately("expect", subject)

fun <T : Any?> expect(subject: T): IAssertionPlantNullable<T>
    = AssertionPlantFactory.newNullable("expect", subject)

inline fun <T : Any> expect(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AssertionPlantFactory.newCheckLazilyAtTheEnd("expect", subject, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent
    = AssertionPlantFactory.throwableFluent("expect the thrown exception", act)
