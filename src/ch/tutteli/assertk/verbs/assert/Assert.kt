package ch.tutteli.assertk.verbs.assert

import ch.tutteli.assertk.creating.AssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactoryNullable

fun <T : Any> assert(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("assert", subject)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = AssertionFactory.newCheckLazilyAtTheEnd("assert", subject, createAssertions)
