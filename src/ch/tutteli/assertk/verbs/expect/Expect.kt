package ch.tutteli.assertk.verbs.expect

import ch.tutteli.assertk.creating.AssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactoryNullable
import ch.tutteli.assertk.creating.ThrowableFluent

fun <T : Any> expect(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("expect", subject)

fun <T : Any?> expect(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("expect", subject)

inline fun <T : Any> expect(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = AssertionFactory.newCheckLazilyAtTheEnd("expect", subject, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent
    = AssertionFactory.throwableFluent("expect the thrown exception", act)
