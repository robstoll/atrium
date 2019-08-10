package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THROWN

/**
 * Creates a [ReportingAssertionContainer] for the given [subject].
 *
 * @return The newly created assertion container.
 *
 * @see CoreFactory.newReportingAssertionContainer
 */
fun <T> assert(subject: T) =
    ExpectImpl.assertionVerbBuilder(subject).withVerb(ASSERT).withDefaultReporter().build()

/**
 * Creates an [ReportingAssertionContainer] for the given [subject] and
 * [ReportingAssertionContainer.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
 * the configured [Reporter]) reported as a whole.
 *
 * @return The newly created assertion container.
 *
 * @see CoreFactory.newReportingAssertionContainer
 */
fun <T> assert(subject: T, assertionCreator: Expect<T>.() -> Unit) =
    assert(subject).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates a [ThrowableThrown.Builder] for the given function [act] which catches a potentially thrown [Throwable]
 * and allows to define an assertion for it.
 *
 * @return The newly created [ThrowableThrown.Builder].
 */
fun assert(act: () -> Unit) = ExpectImpl.throwable.thrownBuilder(ASSERT_THROWN, act, reporter)
