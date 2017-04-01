package ch.tutteli.assertk.verbs.assert

import ch.tutteli.assertk.creating.AssertionPlantFactory
import ch.tutteli.assertk.creating.IAssertionPlant
import ch.tutteli.assertk.creating.IAssertionPlantNullable

fun <T : Any> assert(subject: T): IAssertionPlant<T>
    = AssertionPlantFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionPlantNullable<T>
    = AssertionPlantFactory.newNullable("assert", subject)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AssertionPlantFactory.newCheckLazilyAtTheEnd("assert", subject, createAssertions)
