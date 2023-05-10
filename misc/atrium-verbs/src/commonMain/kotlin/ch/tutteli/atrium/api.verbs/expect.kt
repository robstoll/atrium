package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.api.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.logic.manualFeature

/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The subject for which we are going to postulate expectations.
 *
 * @return The newly created [Expect].
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T): RootExpect<T> =
    RootExpectBuilder.forSubject(subject)
        .withVerb(EXPECT)
        .withoutOptions()
        .build()

/**
 * Creates an [Expect] for the given [subject] and appends the expectations the given
 * [assertionCreator]-lambda creates as group to it.
 *
 * @param subject The subject for which we are going to postulate expectations.
 * @param assertionCreator expectation-group with a non-fail fast behaviour.
 * @return The newly created [Expect].
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(subject)._logic.appendAsGroup(assertionCreator)

/**
 * Creates an [Expect] for the given (unrelated) [newSubject].
 *
 * We recommend to use `its` or `feature` or another feature extractor if you want to extract a feature out of the
 * current subject.
 *
 * @param newSubject The subject for which we are going to postulate expectations.
 * @return The newly created [Expect].
 * @throws AssertionError in case an assertion does not hold.
 *
 * @since 1.0.0
 */
fun <T, R> Expect<T>.expect(newSubject: R): FeatureExpect<T, R> =
    _logic.manualFeature(EXPECT) { newSubject }.transform()

/**
 * Creates an [Expect] for the given (unrelated) [newSubject] and appends the expectations the given
 * [assertionCreator]-lambda creates as group to it.
 *
 * We recommend to use `its` or `feature` or another feature extractor if you want to extract a feature out of the
 * current subject.
 *
 * @param newSubject The new subject for which we are going to postulate expectations.
 * @param assertionCreator expectation-group with a non-fail fast behaviour.
 * @return The newly created [Expect].
 * @throws AssertionError in case an assertion does not hold.
 *
 * @since 1.0.0
 */
fun <T, R> Expect<T>.expect(newSubject: R, assertionCreator: Expect<R>.() -> Unit): Expect<R> =
    _logic.manualFeature(EXPECT) { newSubject }.transformAndAppend(assertionCreator)
