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
 * @param subject The subject for which we are going to postulate assertions.
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
 * @param subject The subject for which we are going to postulate assertions.
 * @param assertionCreator expectation-group with a non-fail fast behaviour.
 * @return The newly created [Expect].
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(subject)._logic.appendAsGroup(assertionCreator)

@Deprecated(
    "`expect` should not be nested, use `feature` or `its` instead.",
    ReplaceWith(
        "feature(\"name of the feature\") { newSubject /* see also other overloads which do not require `name of the feature` and provide the subject as parameter, e.g. feature { f(it::yourFeature) } */}",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.fluent.en_GB.feature"
    )
)
fun <T, R> Expect<T>.expect(newSubject: R): FeatureExpect<T, R> =
    _logic.manualFeature(EXPECT) { newSubject }.transform()
