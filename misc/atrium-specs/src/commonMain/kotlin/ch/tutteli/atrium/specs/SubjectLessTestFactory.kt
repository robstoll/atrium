package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster
import ch.tutteli.atrium.testfactories.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.forElementAndForEachIn

fun <SubjectT> subjectLessTestSetup(
    assertionCreator: Pair<String, Expect<SubjectT>.() -> Unit>,
    otherAssertionCreators: Array<out Pair<String, Expect<SubjectT>.() -> Unit>>,
): TestFactoryBuilder<ExpectTestExecutable>.() -> Unit = {
    forElementAndForEachIn(assertionCreator, otherAssertionCreators) { (name, createAssertion) ->
        it("fun `$name`") {
            @OptIn(ExperimentalComponentFactoryContainer::class)
            val assertions = CollectingExpect<SubjectT>(None, expect(1)._logic.components)
                .appendAsGroup(createAssertion)
                .getAssertions()

            expandAssertionGroups(assertions)

            @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
            val expect = RootExpectBuilder.forSubject(1.0)
                .withVerb("custom assertion verb")
                .withOptions {
                    withComponent(AtriumErrorAdjuster::class) { _ -> NoOpAtriumErrorAdjuster }
                }
                .build()

            val explanatoryGroup = assertionBuilder.explanatoryGroup
                .withDefaultType
                .withAssertions(assertions)
                .build()
            expect._logic.append(explanatoryGroup)
        }
    }
}

fun <T> subjectLessTestFactory(
    assertionCreator: Pair<String, Expect<T>.() -> Unit>,
    vararg otherAssertionCreators: Pair<String, Expect<T>.() -> Unit>,
    groupPrefix: String? = null
): Any = testFactory {
    val setup = subjectLessTestSetup(assertionCreator, otherAssertionCreators)
    if (groupPrefix != null) {
        describe(groupPrefix, setup)
    }
    this.apply(setup)
}

/**
 * Calls recursively [AssertionGroup.assertions] on every expectation-group contained in [assertions].
 */
tailrec fun expandAssertionGroups(assertions: List<Assertion>) {
    if (assertions.isEmpty()) return

    expandAssertionGroups(
        assertions
            .asSequence()
            .filterIsInstance<AssertionGroup>()
            .flatMap { it.assertions.asSequence() }
            .toList()
    )
}

