package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.verbs.AssertionVerb.EXPECT_THROWN

/**
 * Creates a [ReportingAssertionContainer] for the given [subject].
 *
 * @return The newly created assertion container.
 *
 * @see CoreFactory.newReportingAssertionContainer
 */
fun <T> expect(subject: T)
    = AssertImpl.coreFactory.newReportingAssertionContainer(EXPECT, { subject }, reporter)

/**
 * Creates an [ReportingAssertionContainer] for the given [subject] and
 * [ReportingAssertionContainer.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
 * the configured [Reporter]) reportBuilder as a whole.
 *
 * @return The newly created assertion container.
 *
 * @see CoreFactory.newReportingAssertionContainer
 */
fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit)
    = expect(subject).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates a [ThrowableThrown.Builder] for the given function [act] which is expected to throw a [Throwable].
 *
 * @return The newly created [ThrowableThrown.Builder].
 */
//TODO uses an AssertionPlant internally which is wrong
fun expect(act: () -> Unit)
    = AssertImpl.throwable.thrownBuilder(EXPECT_THROWN, act, reporter)
