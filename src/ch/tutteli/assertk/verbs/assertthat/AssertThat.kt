package ch.tutteli.assertk.verbs.assertthat

import ch.tutteli.assertk.creating.AssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactoryNullable

fun <T : Any> assertThat(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert that", subject)

fun <T : Any?> assertThat(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("assert that", subject)

inline fun <T : Any> assertThat(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = AssertionFactory.newCheckLazilyAtTheEnd("assert that", subject, createAssertions)
