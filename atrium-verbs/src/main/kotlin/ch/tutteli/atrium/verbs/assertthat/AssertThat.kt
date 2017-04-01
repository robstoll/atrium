package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.creating.AssertionPlantFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

fun <T : Any> assertThat(subject: T): IAssertionPlant<T>
    = AssertionPlantFactory.newCheckImmediately("assert that", subject)

fun <T : Any?> assertThat(subject: T): IAssertionPlantNullable<T>
    = AssertionPlantFactory.newNullable("assert that", subject)

inline fun <T : Any> assertThat(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AssertionPlantFactory.newCheckLazilyAtTheEnd("assert that", subject, createAssertions)
