package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.factories.InternalExpectationVerbs
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.api.verbs.internal.testfactories.impl.ExpectGroupedBasedExpectTestExecutableForTestsImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.integration.utils.subjectLessTestSetup
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.uncheckedToNonNullable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.testfactories.buildTestNodes
import ch.tutteli.atrium.testfactories.expect.grouped.impl.turnTestNodesIntoExpectGrouping

actual abstract class ExpectationFunctionBaseTest {

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ): Any = this.let { self ->
        testFactory {
            describe("${self::class.simpleName} - ${specPair.first}") { setup(specPair.lambda) }
        }
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: SpecPair<Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ): Any = this.let { self ->
        testFactory {
            describe(
                "${self::class.simpleName} - subjectLessTest${groupPrefix?.let { " - $it" } ?: ""}",
                subjectLessTestSetup(expectationCreator, otherExpectationCreators)
            )
        }
    }

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): Any = this.let { self ->
        testFactory {
            describe("${self::class.simpleName} - subjectLessTest") {
                applySubjectLessTestSetup(testData, otherTestData)
            }
        }
    }

    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ): Any = expectationCreatorTestFactory(
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
    ): Any = this.let { self ->
        testFactory {
            describe("${self::class.simpleName} - expectationCreatorTest") {
                applyExpectationCreatorTestSetup(testData, otherTestData)
            }
        }
    }

    protected actual fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit
    ): Any = turnTestNodesIntoExpectGrouping(
        buildTestNodes<ExpectTestExecutableForTests> {
            uncheckedToNonNullable(nonNullableSpecPair, nullableSpecPair).forEach { specPair ->
                itFun(specPair, testExecutable)
            }
        }
    ) { ExpectGroupedBasedExpectTestExecutableForTestsImpl(InternalExpectationVerbs) }

}
