package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.api.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.api.verbs.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reporter

/**
 * Creates a [ReportingAssertionContainer] for the given [subject].
 *
 * @return The newly created assertion container.
 *
 * @see CoreFactory.newReportingAssertionContainer
 */
fun <T> expect(subject: T): Expect<T> =
    ExpectImpl.assertionVerbBuilder(subject).withVerb(EXPECT).withDefaultReporter().build()

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
fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(subject).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates a [ThrowableThrown.Builder] for the given function [act] which catches a potentially thrown [Throwable]
 * and allows to define an assertion for it.
 *
 * @return The newly created [ThrowableThrown.Builder].
 */
fun expect(act: () -> Unit) = ExpectImpl.throwable.thrownBuilder(EXPECT_THROWN, act, reporter)

@Deprecated(
    "`expect` should not be nested, use `feature` instead.",
    ReplaceWith("feature(\"name of the feature\") { newSubject /* see also other overloads which do not require `name of the feature` and provide the subject as parameter, e.g. feature { f(it::yourFeature) } */}",
        "ch.tutteli.atrium.api.fluent.de_CH.feature",
        "ch.tutteli.atrium.api.fluent.en_GB.feature"
    )
)
fun <T, R> Expect<T>.expect(newSubject: R): Expect<R> =
    ExpectImpl.feature.manualFeature(this, EXPECT) { newSubject }.getExpectOfFeature()
