package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Feature1
import ch.tutteli.atrium.specs.Feature2
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.uncheckedToNonNullable
import ch.tutteli.atrium.specs.withSubExpectation
import ch.tutteli.atrium.testfactories.*
import kotlin.reflect.KProperty0
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
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText { specPair.name }) { setup(specPair.lambda) }
    }

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        otherSpecPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(fun1: T, fun2: T) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText { "fun `${specPair.name}` and `${otherSpecPair.name}`" }) {
            setup(specPair.lambda, otherSpecPair.lambda)
        }
    }

    protected actual fun testFactory(
        specPair: SpecPair<*>,
        otherSpecPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText {
            (listOf(specPair, otherSpecPair) + otherSpecPairs).joinToString(", ") { "`${it.name}`" }
        }) {
            setup()
        }
    }

    protected actual fun <T> testFactoryDescribeSameBehaviour(
        specPair: SpecPair<T>,
        otherSpecPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit,
    ) = internalTestFactory {
        describe(getDescribeText { "fun `${specPair.name}` and `${otherSpecPair.name}`" }) {
            describeFun(specPair, setup)
            describeFun(otherSpecPair, setup)
        }
    }

    protected actual fun <T, R> testFactoryForFeatureNonFeature(
        f0: Feature0<T, R>,
        f1: Fun1<T, Expect<R>.() -> Unit>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<T>, hasExtraHint: Boolean) -> Unit,
    ) = internalTestFactory {
        describe(getDescribeText { "fun `${f0.name}` and `${f1.name}`" }) {
            this.setup(f0.name, f0.withSubExpectation(), false)
            this.setup(f1.name, f1.lambda, true)
        }
    }

    protected actual fun <T, A1, R> testFactoryForFeatureNonFeature(
        f0: Feature1<T, A1, R>,
        f1: Fun2<T, A1, Expect<R>.() -> Unit>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<T>, hasExtraHint: Boolean) -> Unit,
    ) = internalTestFactory {
        describe(getDescribeText { "fun `${f0.name}` and `${f1.name}`" }) {
            this.setup(f0.name, f0.withSubExpectation(), false)
            this.setup(f1.name, f1.lambda, true)
        }
    }


    protected actual fun <T, R> testFactoryForFeatureNonFeature(
        f0: Feature0<T, R>,
        f1: Feature1<T, Expect<R>.() -> Unit, R>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>, hasExtraHint: Boolean) -> Unit
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText { "fun `${f0.name}` and `${f1.name}`" }) {
            val f0WithSubAssertion: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R> =
                { f: Expect<R>.() -> Unit -> (f0.lambda)().apply(f) }
            this.setup(f0.name, f0WithSubAssertion, false)
            this.setup(f1.name, f1.lambda, true)
        }
    }

    protected actual fun <T, A1, R> testFactoryForFeatureNonFeature(
        f0: Feature1<T, A1, R>,
        f1: Feature2<T, A1, Expect<R>.() -> Unit, R>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R>, hasExtraHint: Boolean) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText { "fun `${f0.name}` and `${f1.name}`" }) {
            val f0WithSubAssertion: Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R> =
                { a1, f: Expect<R>.() -> Unit -> (f0.lambda)(a1).apply(f) }
            this.setup(f0.name, f0WithSubAssertion, false)
            this.setup(f1.name, f1.lambda, true)
        }
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: SpecPair<Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ): PlatformTestNodeContainer<PlatformTestNode> = subjectLessTestFactory(
        SubjectLessTestData(expectationCreator, *otherExpectationCreators, groupPrefix = groupPrefix)
    )

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText { "subjectLess" }) {
            applySubjectLessTestSetup(testData, otherTestData)
        }
    }

    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ): PlatformTestNodeContainer<PlatformTestNode> = expectationCreatorTestFactory(
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
    ): PlatformTestNodeContainer<PlatformTestNode> = internalTestFactory {
        describe(getDescribeText { "expectationCreatorTest" }) {
            applyExpectationCreatorTestSetup(testData, otherTestData)
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

    private inline fun getDescribeText(ifCurrentTestNameAbsent: () -> String): String =
        if (js("globalThis.__currentTestName__") == null) {
            "${this::class.simpleName} - ${ifCurrentTestNameAbsent()}"
        } else {
            "${this::class.simpleName}"
        }
}

actual fun TestFactoryBuilder<ExpectTestExecutableForTests>.describeIterable(
    iterableProvider: KProperty0<Function0<Iterable<*>>>,
    setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit
) {
    val iterable = iterableProvider.invoke().invoke().toList()
    describe("iterable ${if (iterable.size <= 4) iterable.joinToString(", ") else iterableProvider.name}", setup)
}
