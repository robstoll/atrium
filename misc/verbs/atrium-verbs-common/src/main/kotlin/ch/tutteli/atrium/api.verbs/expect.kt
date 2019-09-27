package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.api.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.api.verbs.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reporter

/**
 * Creates an [Expect] for the given [subject].
 *
 * @return The newly created assertion container.
 */
fun <T> expect(subject: T, representation: String? = null, options: ExpectOptions = ExpectOptions()): Expect<T> =
    ExpectBuilder.forSubject(subject)
        .withVerb(EXPECT)
        .withOptions(options.merge(ExpectOptions(representation = representation?.let { RawString.create(it) })))
        .build()

/**
 * Creates an [Expect] for the given [subject] and [Expect.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
 * the configured [Reporter]) reported as a whole.
 *
 * @return The newly created assertion container.
 */
fun <T> expect(
    subject: T,
    representation: String? = null,
    options: ExpectOptions = ExpectOptions(),
    assertionCreator: Expect<T>.() -> Unit
): Expect<T> = expect(subject, representation, options).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates a [ThrowableThrown.Builder] for the given function [act] which catches a potentially thrown [Throwable]
 * and allows to define an assertion for it.
 *
 * @return The newly created [ThrowableThrown.Builder].
 */
fun expect(act: () -> Unit): ThrowableThrown.Builder = ExpectImpl.throwable.thrownBuilder(EXPECT_THROWN, act, reporter)

// TODO #97 now we are almost there to implement expect{}.toThrow in terms of expect
//fun <R> expect(
//    representation: String? = null,
//    options: ExpectOptions = ExpectOptions(),
//    act: () -> R
//): Expect<() -> R> = expect(act, representation, ExpectOptions {
//    withVerb(EXPECT_THROWN)
//    withNullRepresentation(RawString.create("no exception occurred"))
//}.merge(options))

@Deprecated(
    "`expect` should not be nested, use `feature` instead.",
    ReplaceWith(
        "feature(\"name of the feature\") { newSubject /* see also other overloads which do not require `name of the feature` and provide the subject as parameter, e.g. feature { f(it::yourFeature) } */}",
        "ch.tutteli.atrium.api.fluent.de_CH.feature",
        "ch.tutteli.atrium.api.fluent.en_GB.feature"
    )
)
fun <T, R> Expect<T>.expect(newSubject: R): Expect<R> =
    ExpectImpl.feature.manualFeature(this, EXPECT) { newSubject }.getExpectOfFeature()
