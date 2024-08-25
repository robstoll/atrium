package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.api.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.api.verbs.factory.DefaultExpectationVerbs
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.testfactories.testFactoryTemplate

import ch.tutteli.atrium.api.verbs.expect as atriumVerb

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
    atriumVerb(subject)._logic.appendAsGroup(assertionCreator)

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
 * Consider to use [expectGrouped] instead of [ch.tutteli.atrium.api.verbs.expect] as expectation entry point if you
 * want to state expectations about several unrelated subjects. [expectGrouped] fulfills exactly this purpose.
 *
 * We recommend to use `its` or `feature` or another feature extractor if you want to extract a feature out of the
 * current subject.
 *
 * @param newSubject The new subject for which we are going to postulate expectations.
 * @param assertionCreator expectation-group with a non-fail fast behaviour.
 * @return The newly created [Expect].
 *
 * @since 1.0.0
 */
fun <T, R> Expect<T>.expect(newSubject: R, assertionCreator: Expect<R>.() -> Unit): Expect<R> =
    _logic.manualFeature(EXPECT) { newSubject }.transformAndAppend(assertionCreator)

/**
 * Creates an [ExpectGrouping] which can be used to group multiple unrelated subjects.
 *
 * @param description Description of the root group.
 * @param groupingActions Some action which defines what happens within the group (typically, creating some
 *   expectations via [ch.tutteli.atrium.api.verbs.expect] or nesting the grouping further).
 * @param configuration, Optionally, you can define more options via [RootExpectBuilder.OptionsChooser] such
 *   as exchange components etc -- experimental.
 *
 * @since 1.1.0
 */
@OptIn(ExperimentalNewExpectTypes::class)
fun expectGrouped(
    description: String = AssertionVerb.EXPECT_GROUPED.getDefault(),
    configuration: RootExpectBuilder.OptionsChooser<*>.() -> Unit = {},
    groupingActions: ExpectGrouping.() -> Unit,
): ExpectGrouping = RootExpectBuilder.forSubject(Text.EMPTY)
    .withVerb(description)
    .withOptions {
        configuration()
    }
    .build()
    ._logic.appendAsGroup(groupingActions.toAssertionCreator())
    .toExpectGrouping()


/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The new subject for which we are going to postulate expectations.
 * @return The newly created [Expect].
 *
 * @since 1.1.0
 */
fun <T> ExpectGrouping.expect(subject: T): Expect<T> =
    expectWithinExpectGroup(subject).transform()

/**
 * Creates an [Expect] for the given [subject] and appends the expectations the given
 * [assertionCreator]-lambda creates as group to it.
 *
 * @param subject The new subject for which we are going to postulate expectations.
 * @param assertionCreator has to create at least one expectation where all are wrapped into an expectation-group
 *   with a non-fail fast behaviour.
 * @return The newly created [Expect].
 *
 * @since 1.1.0
 */
fun <T> ExpectGrouping.expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expectWithinExpectGroup(subject).transformAndAppend(assertionCreator)


private fun <T> ExpectGrouping.expectWithinExpectGroup(subject: T) =
    _logic.manualFeature(EXPECT) { subject }

/**
 * In order to have one way only, use the function provided by the API such as `group`.
 *
 * You should basically only have one top `expectGrouped` as entry point and then only use functionality from the API.
 *
 * @since 1.1.0
 */
@Deprecated("use `group` instead", ReplaceWith("this.group(description, representationProvider, groupingActions)"))
fun ExpectGrouping.expectGrouped(
    description: String,
    representationProvider: () -> Any = Text.EMPTY_PROVIDER,
    groupingActions: ExpectGrouping.() -> Unit
): ExpectGrouping = _logicAppend { grouping(description, representationProvider, groupingActions) }

/**
 * Creates a test factory which can be used in conjunction with Atrium's [TestFactory] annotation and uses Atrium's
 * default expectation verbs.
 *
 * @return The platform specific test factory.
 * @since 1.3.0
 */
fun testFactory(setup: TestFactoryBuilder.() -> Unit): Any = testFactoryTemplate(setup, DefaultExpectationVerbs)

/**
 * Creates a test factory which can be used in conjunction with Atrium's [TestFactory] annotation and uses Atrium's
 * default expectation verbs.
 *
 * @return The platform specific test factory.
 * @since 1.3.0
 */
fun testFactory(
    setup: TestFactoryBuilder.() -> Unit,
    vararg otherSetups: TestFactoryBuilder.() -> Unit
): Any = testFactoryTemplate(setup, otherSetups, DefaultExpectationVerbs)
