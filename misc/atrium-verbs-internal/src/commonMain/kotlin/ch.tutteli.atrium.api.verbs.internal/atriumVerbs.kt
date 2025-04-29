package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.api.verbs.internal.factories.InternalExpectationVerbs
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.api.verbs.internal.testfactories.impl.RootExpectTestExecutableForTestsImpl
import ch.tutteli.atrium._core
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.logic.toAssertionCreator
import ch.tutteli.atrium.logic.toExpectGrouping
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.erroradjusters.MultiAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler
import ch.tutteli.atrium.reporting.theming.text.impl.MarkdownTextIconStyler
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.testfactories.testFactoryTemplate

@OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(subject: T): RootExpect<T> =
    RootExpectBuilder.forSubject(subject)
        .withVerb("I expected subject")
        .withOptions {
            withComponent(AtriumErrorAdjuster::class) { c ->
                MultiAtriumErrorAdjuster(
                    c.build<RemoveRunnerFromAtriumError>(),
                    RemoveAtriumButNotAtriumSpecsFromAtriumErrorImpl(),
                    otherAdjusters = emptyList()
                )
            }
            withComponent(TextIconStyler::class) {
                MarkdownTextIconStyler()
            }
        }
        .build()

fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(subject)._core.appendAsGroupIndicateIfOneCollected(
        ExpectationCreatorWithUsageHints(
            // we don't have an alternative, we always expect expectations and hence we don't provide a failure hint
            // (proposing `expect(subject).` as alternative would be wrong as we also expect further expectations)
            usageHintsAlternativeWithoutExpectationCreator = emptyList(),
            expectationCreator = assertionCreator
        )
    ).first


@OptIn(ExperimentalNewExpectTypes::class)
fun expectGrouped(
    description: String = "my expectations",
    configuration: RootExpectBuilder.OptionsChooser<*>.() -> Unit = {},
    groupingActions: ExpectGrouping.() -> Unit,
): ExpectGrouping = RootExpectBuilder.forSubject(Text.EMPTY)
    .withVerb(description)
    .withOptions {
        configuration()
    }
    .build()
    ._core.appendAsGroupIndicateIfOneCollected(
        ExpectationCreatorWithUsageHints(
            // we don't have an alternative, we always expect sub-expectations and hence we don't provide a failure hint
            usageHintsAlternativeWithoutExpectationCreator = emptyList(),
            expectationCreator = groupingActions.toAssertionCreator()
        )
    ).first
    .toExpectGrouping()


fun <R> ExpectGrouping.expect(subject: R): Expect<R> =
    expectWithinExpectGroup(subject).transform()

fun <R> ExpectGrouping.expect(subject: R, assertionCreator: Expect<R>.() -> Unit): Expect<R> =
    expectWithinExpectGroup(subject).transformAndAppend(assertionCreator)

@Suppress("DEPRECATION")
private fun <R> ExpectGrouping.expectWithinExpectGroup(subject: R) =
    //TODO 1.3.0 change to _core
    _logic.manualFeature("I expected subject") { subject }

expect class RemoveAtriumButNotAtriumSpecsFromAtriumErrorImpl() : RemoveAtriumFromAtriumError {
    override fun adjust(throwable: Throwable)
    override fun adjustOtherThanStacks(throwable: Throwable)
}

fun testFactory(setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit) =
    testFactoryTemplate(setup, createExpectTestExecutableForTestsFactory())

fun testFactory(
    setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    vararg otherSetups: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit
) = testFactoryTemplate(setup, otherSetups, createExpectTestExecutableForTestsFactory())

private fun createExpectTestExecutableForTestsFactory(
    expectationVerbs: ExpectationVerbs = InternalExpectationVerbs
): () -> ExpectTestExecutableForTests = {
    RootExpectTestExecutableForTestsImpl(expectationVerbs)
}
