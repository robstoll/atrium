package ch.tutteli.assertk.verbs.expect

import ch.tutteli.assertk.checking.ThrowingAssertionChecker
import ch.tutteli.assertk.createAndCheckAssertions
import ch.tutteli.assertk.creating.AssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactoryNullable
import ch.tutteli.assertk.creating.ThrowableFluent
import ch.tutteli.assertk.reporting.DetailedObjectFormatter
import ch.tutteli.assertk.reporting.OnlyFailureReporter
import ch.tutteli.assertk.reporting.SameLineAssertionMessageFormatter

fun <T : Any> expect(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("expect", subject)

fun <T : Any?> expect(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("expect", subject)

inline fun <T : Any> expect(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = createAndCheckAssertions("expect", subject, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent {
    val objectFormatter = DetailedObjectFormatter()
    val assertionMessageFormatter = SameLineAssertionMessageFormatter(objectFormatter)
    val reporter = OnlyFailureReporter(assertionMessageFormatter)
    return ThrowableFluent.create("expect to throw", act, ThrowingAssertionChecker(reporter))
}
