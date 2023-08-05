package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster

/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The subject for which we are going to postulate expectations.
 *
 * @return The newly created [RootExpect].
 * @throws AssertionError in case an assertion does not hold.
 */
@OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(subject: T): RootExpect<T> =
    RootExpectBuilder.forSubject(subject)
        .withVerb("I expected subject")
        .withOptions {
            //TODO 1.2.0 we could at least filter out the runner in most cases, even in tests
            withComponent(AtriumErrorAdjuster::class) { _ -> NoOpAtriumErrorAdjuster }
        }
        .build()

/**
 * Creates an [Expect] for the given [subject] and appends the assertions create by the given
 * [assertionCreator]-lambda where the created [Assertion]s are added as a group and reported as a whole.
 *
 * @param subject The subject for which we are going to postulate expectations.
 * @param assertionCreator expectation-group with a non-fail fast behaviour.
 *
 * @return The newly created [RootExpect].
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(subject)._logic.appendAsGroup(assertionCreator)

