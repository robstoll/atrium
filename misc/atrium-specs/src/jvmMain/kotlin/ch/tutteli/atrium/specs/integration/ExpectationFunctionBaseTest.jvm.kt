package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.integration.utils.expectationCreatorTestSetup
import ch.tutteli.atrium.specs.integration.utils.subjectLessTestSetup
import ch.tutteli.atrium.testfactories.PlatformTestNode
import ch.tutteli.atrium.testfactories.PlatformTestNodeContainer
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.joinToString
import kotlin.reflect.KFunction0
import kotlin.reflect.KProperty0
import ch.tutteli.atrium.api.verbs.internal.testFactory as internalTestFactory

actual abstract class ExpectationFunctionBaseTest {

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ) = internalTestFactory {
        describeFun(specPair, setup)
    }

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        otherSpecPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(fun1: T, fun2: T) -> Unit,
    ) = internalTestFactory {
        describe("fun `${specPair.name}` and `${otherSpecPair.name}`") {
            setup(specPair.lambda, otherSpecPair.lambda)
        }
    }

    protected actual fun testFactory(
        specPair: SpecPair<*>,
        otherSpecPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    ) = internalTestFactory {
        describe(
            "fun ${
                (listOf(specPair, otherSpecPair) + otherSpecPairs).joinToString(
                    separator = ", ",
                    lastSeparator = " and "
                ) { pair, sb -> sb.append("`").append(pair.name).append("`") }
            }"
        ) {
            setup()
        }
    }

    protected actual fun <T> testFactoryDescribeSameBehaviour(
        specPair: SpecPair<T>,
        otherSpecPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit,
    ) = internalTestFactory {
        describeFun(specPair, setup)
        describeFun(otherSpecPair, setup)
    }

    @JvmName("testFactoryForFeature0NonFun1")
    protected actual fun <T, R> testFactoryForFeatureNonFeature(
        f0: Feature0<T, R>,
        f1: Fun1<T, Expect<R>.() -> Unit>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<T>, hasExtraHint: Boolean) -> Unit,
    ) = internalTestFactory {
        describe("fun `${f0.name}` and `${f1.name}`") {
            setup(f0.name, f0.withSubExpectation(), false)
            setup(f1.name, f1.lambda, true)
        }
    }

    @JvmName("testFactoryForFeature1NonFun2")
    protected actual fun <T, A1, R> testFactoryForFeatureNonFeature(
        f0: Feature1<T, A1, R>,
        f1: Fun2<T, A1, Expect<R>.() -> Unit>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<T>, hasExtraHint: Boolean) -> Unit,
    ) = internalTestFactory {
        describe("fun `${f0.name}` and `${f1.name}`") {
            setup(f0.name, f0.withSubExpectation(), false)
            setup(f1.name, f1.lambda, true)
        }
    }

    @JvmName("testFactoryForFeature0NonFeature1")
    protected actual fun <T, R> testFactoryForFeatureNonFeature(
        f0: Feature0<T, R>,
        f1: Feature1<T, Expect<R>.() -> Unit, R>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>, hasExtraHint: Boolean) -> Unit,
    ) = internalTestFactory {
        describe("fun `${f0.name}` and `${f1.name}`") {
            val f0WithSubAssertion: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R> =
                { f: Expect<R>.() -> Unit -> (f0.lambda)().apply(f) }
            setup(f0.name, f0WithSubAssertion, false)
            setup(f1.name, f1.lambda, true)
        }
    }

    @JvmName("testFactoryForFeature1NonFeature2")
    protected actual fun <T, A1, R> testFactoryForFeatureNonFeature(
        f0: Feature1<T, A1, R>,
        f1: Feature2<T, A1, Expect<R>.() -> Unit, R>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R>, hasExtraHint: Boolean) -> Unit,
    ) = internalTestFactory {
        describe("fun `${f0.name}` and `${f1.name}`") {
            val f0WithSubAssertion: Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R> =
                { a1, f: Expect<R>.() -> Unit -> (f0.lambda)(a1).apply(f) }
            setup(f0.name, f0WithSubAssertion, false)
            setup(f1.name, f1.lambda, true)
        }
    }

    protected actual fun <T : Any> testFactoryNonNullable(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        uncheckedToNonNullable(nonNullableSpecPair, nullableSpecPair).forEach { specPair ->
            describeFun(specPair, setup = setup)
        }
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: Pair<String, Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ) = internalTestFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}check expectation function can be used in a proof explanation without subject defined and does not throw") {
            apply(subjectLessTestSetup(expectationCreator, otherExpectationCreators))
        }
    }

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ) = internalTestFactory {
        describe("check expectation function can be used in a proof explanation without subject defined and does not throw") {
            applySubjectLessTestSetup(testData, otherTestData)
        }
    }


    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ) = internalTestFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}specifying two expectationCreator-lambda, one is empty") {
            apply(expectationCreatorTestSetup(subject, assertionCreator, otherAssertionCreators))
        }
    }

    protected actual fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ) = internalTestFactory {
        describe("specifying two expectationCreator-lambda, one is empty") {
            applyExpectationCreatorTestSetup(testData, otherTestData)
        }
    }

}

actual fun TestFactoryBuilder<ExpectTestExecutableForTests>.describeIterable(
    iterableProvider: KProperty0<Function0<Iterable<*>>>,
    setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit
): Unit = describe("iterable ${iterableProvider().invoke().joinToString()}", setup)
