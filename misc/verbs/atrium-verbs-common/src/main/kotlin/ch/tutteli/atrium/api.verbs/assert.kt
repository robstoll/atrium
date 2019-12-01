package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.api.verbs.AssertionVerb.ASSERT
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions

/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The subject for which we are going to postulate assertions.
 * @param representation Optional, use it in case you want to use a custom representation for the subject.
 * @param options Optional, use it in case you want to tweak the resulting [Expect], for instance, use another reporter.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> assert(subject: T, representation: String? = null, options: ExpectOptions? = null): ReportingAssertionContainer<T> =
    ExpectBuilder.forSubject(subject)
        .withVerb(ASSERT)
        .withMaybeRepresentationAndMaybeOptions(representation, options)
        .build()

/**
 * Creates an [Expect] for the given [subject] and [Expect.addAssertionsCreatedBy] the
 * given [assertionCreator]-lambda where the created [Assertion]s are added as a group and reported as a whole.
 *
 * @param subject The subject for which we are going to postulate assertions.
 * @param representation Optional, use it in case you want to use a custom representation for the subject.
 * @param options Optional, use it in case you want to tweak the resulting [Expect], for instance, use another reporter.
 *
 * @param assertionCreator Assertion group block with a non-fail fast behaviour.
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> assert(
    subject: T,
    representation: String? = null,
    options: ExpectOptions? = null,
    assertionCreator: Expect<T>.() -> Unit
): ReportingAssertionContainer<T> = assert(subject, representation, options).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates an [Expect] with the given [act]-lambda as subject.
 *
 * @param act the subject for which we are going to postulate assertions.
 * @param options Optional, use it in case you want to tweak the resulting [Expect], for instance, use another reporter.
 *
 * @param representation Optional, use it in case you want to use a custom representation for the subject.
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
//note the order of the parameters (options before representation) is this way to disambiguate calls
fun <R> assert(
    options: ExpectOptions? = null,
    representation: String? = null,
    act: () -> R
): ReportingAssertionContainer<() -> R> = assert(act, representation, options)

@Deprecated(
    "`assert` should not be nested, use `feature` instead.",
    ReplaceWith(
        "feature(\"name of the feature\") { newSubject /* see also other overloads which do not require `name of the feature` and provide the subject as parameter, e.g. feature { f(it::yourFeature) } */}",
        "ch.tutteli.atrium.api.fluent.de_CH.feature",
        "ch.tutteli.atrium.api.fluent.en_GB.feature"
    )
)
fun <T, R> Expect<T>.assert(newSubject: R): Expect<R> =
    ExpectImpl.feature.manualFeature(this, ASSERT) { newSubject }.getExpectOfFeature()
