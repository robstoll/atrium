package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.factories.InternalExpectationVerbs
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.api.verbs.internal.testfactories.impl.ExpectGroupedBasedExpectTestExecutableForTestsImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.uncheckedToNonNullable
import ch.tutteli.atrium.testfactories.*
import ch.tutteli.atrium.testfactories.expect.grouped.impl.turnTestNodesIntoExpectGrouping
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.api.verbs.internal.testFactory as internalTestFactory

actual abstract class ExpectationFunctionBaseTest {
    init {
        val mocha = js("global")
        if (mocha.beforeEach != null && mocha.afterEach != null) {
            mocha.beforeEach {
                // `this.currentTest` is available here because Mocha calls this function
                js("globalThis.__currentTestName__ = this.currentTest.title")
            }
            mocha.afterEach {
                js("delete globalThis.__currentTestName__")
            }
        }
    }

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ): DynamicNodeContainer<DynamicNodeLike> = internalTestFactory {
        describe(getDescribeText { specPair.name }) { setup(specPair.lambda) }
    }

    protected actual fun testFactory(
        specPair: SpecPair<*>,
        otherSpecPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    ): DynamicNodeContainer<DynamicNodeLike> = internalTestFactory {
        describe(getDescribeText {
            (listOf(specPair, otherSpecPair) + otherSpecPairs).joinToString(", ") { "`${it.name}`" }
        }) {
            setup()
        }
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: SpecPair<Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ): DynamicNodeContainer<DynamicNodeLike> = subjectLessTestFactory(
        SubjectLessTestData(expectationCreator, *otherExpectationCreators, groupPrefix = groupPrefix)
    )

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): DynamicNodeContainer<DynamicNodeLike> = internalTestFactory {
        describe(getDescribeText { "subjectLess" }) {
            applySubjectLessTestSetup(testData, otherTestData)
        }
    }

    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ): DynamicNodeContainer<DynamicNodeLike> = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            subject,
            assertionCreator,
            *otherAssertionCreators,
            groupPrefix = groupPrefix
        )
    )

    protected actual fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ): DynamicNodeContainer<DynamicNodeLike> = internalTestFactory {
        describe(getDescribeText { "expectationCreatorTest" }) {
            applyExpectationCreatorTestSetup(testData, otherTestData)
        }
    }

    protected actual fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit
    ): DynamicNodeContainer<DynamicNodeLike> = UnitDynamicNodeContainer.also {
        turnTestNodesIntoExpectGrouping(
            buildTestNodes<ExpectTestExecutableForTests> {
                uncheckedToNonNullable(nonNullableSpecPair, nullableSpecPair).forEach { specPair ->
                    itFun(specPair, setup = testExecutable)
                }
            }
        ) { ExpectGroupedBasedExpectTestExecutableForTestsImpl(InternalExpectationVerbs) }
    }

    private inline fun getDescribeText(ifCurrentTestNameAbsent: () -> String): String =
        if (js("globalThis.__currentTestName__") == null) {
            "${this::class.simpleName} - ${ifCurrentTestNameAbsent()}"
        } else {
            "${this::class.simpleName}"
        }
}
