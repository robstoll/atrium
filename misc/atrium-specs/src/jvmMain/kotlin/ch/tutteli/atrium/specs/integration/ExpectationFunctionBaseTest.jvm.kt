package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.integration.utils.expectationCreatorTestSetup
import ch.tutteli.atrium.specs.integration.utils.subjectLessTestSetup
import ch.tutteli.atrium.testfactories.TestFactoryBuilder


actual abstract class ExpectationFunctionBaseTest {

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ): Any = testFactory {
        describeFun(specPair, setup)
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: Pair<String, Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ): Any = testFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}check expectation function can be used in a proof explanation without subject defined and does not throw") {
            this.apply(subjectLessTestSetup(expectationCreator, otherExpectationCreators))
        }
    }

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): Any = testFactory {
        describe("check expectation function can be used in a proof explanation without subject defined and does not throw") {
            applySubjectLessTestSetup(testData, otherTestData)
        }
    }


    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ): Any = testFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}specifying two expectationCreator-lambda, one is empty") {
            this.apply(expectationCreatorTestSetup(subject, assertionCreator, otherAssertionCreators))
        }
    }

    protected actual fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ): Any = testFactory {
        describe("specifying two expectationCreator-lambda, one is empty") {
            applyExpectationCreatorTestSetup(testData, otherTestData)
        }
    }

    protected actual fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit,
    ): Any = testFactory {
        describe("non-nullable cases") {
            uncheckedToNonNullable(nonNullableSpecPair, nullableSpecPair)
                .forEach { specPair -> itFun(specPair, testExecutable) }
        }
    }
}
