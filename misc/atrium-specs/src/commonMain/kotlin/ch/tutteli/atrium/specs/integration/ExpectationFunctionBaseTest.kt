package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.*
import ch.tutteli.atrium.testfactories.PlatformTestNode
import ch.tutteli.atrium.testfactories.PlatformTestNodeContainer
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.forElementAndForEachIn
import kotlin.reflect.KProperty0

expect abstract class ExpectationFunctionBaseTest() {

    protected fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <T> testFactory(
        specPair: SpecPair<T>,
        otherSpecPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(fun1: T, fun2: T) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun testFactory(
        specPair: SpecPair<*>,
        otherSpecPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    /**
     * Executes twice the given [setup] once with [specPair] and once with [otherSpecPair].
     */
    protected fun <T> testFactoryDescribeSameBehaviour(
        specPair: SpecPair<T>,
        otherSpecPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(bothFun: T) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <T, R> testFactoryForFeatureNonFeature(
        f0: Feature0<T, R>,
        f1: Fun1<T, Expect<R>.() -> Unit>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<T>, hasExtraHint: Boolean) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <T, A1, R> testFactoryForFeatureNonFeature(
        f0: Feature1<T, A1, R>,
        f1: Fun2<T, A1, Expect<R>.() -> Unit>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<T>, hasExtraHint: Boolean) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <T, R> testFactoryForFeatureNonFeature(
        f0: Feature0<T, R>,
        f1: Feature1<T, Expect<R>.() -> Unit, R>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>, hasExtraHint: Boolean) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <T, A1, R> testFactoryForFeatureNonFeature(
        f0: Feature1<T, A1, R>,
        f1: Feature2<T, A1, Expect<R>.() -> Unit, R>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(name: String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R>, hasExtraHint: Boolean) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <T : Any> testFactoryNonNullable(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <SubjectT> subjectLessTestFactory(
        expectationCreator: SpecPair<Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String? = null
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: ExpectationCreatorTriple<SubjectT>,
        vararg otherAssertionCreators: ExpectationCreatorTriple<SubjectT>,
        groupPrefix: String? = null
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ): PlatformTestNodeContainer<PlatformTestNode>

}

fun TestFactoryBuilder<ExpectTestExecutableForTests>.applySubjectLessTestSetup(
    testData: SubjectLessTestData<*>,
    otherTestData: Array<out SubjectLessTestData<*>>
) {
    forElementAndForEachIn(testData, otherTestData) { data ->
        describeIfNonNull(data.groupPrefix) {
            @Suppress("UNCHECKED_CAST") val e = data.expectationCreator as SpecPair<Expect<Any?>.() -> Unit>
            @Suppress("UNCHECKED_CAST") val oE =
                data.otherExpectationCreators as Array<out SpecPair<Expect<Any?>.() -> Unit>>
            apply(subjectLessTestSetup(e, oE))
        }
    }
}

fun TestFactoryBuilder<ExpectTestExecutableForTests>.applyExpectationCreatorTestSetup(
    testData: ExpectationCreatorTestData<*>,
    otherTestData: Array<out ExpectationCreatorTestData<*>>
) {
    forElementAndForEachIn(testData, otherTestData) { data ->
        describeIfNonNull(data.groupPrefix) {
            @Suppress("UNCHECKED_CAST") val e = data.expectationCreator as ExpectationCreatorTriple<Any?>
            @Suppress("UNCHECKED_CAST") val oE =
                data.otherExpectationCreators as Array<out ExpectationCreatorTriple<Any?>>
            apply(expectationCreatorTestSetup(data.subject, e, oE))
        }
    }
}

private fun TestFactoryBuilder<ExpectTestExecutableForTests>.describeIfNonNull(
    groupPrefix: String?,
    setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit
) {
    if (groupPrefix != null) {
        describe(groupPrefix) {
            setup()
        }
    } else {
        setup()
    }
}

fun <T> TestFactoryBuilder<ExpectTestExecutableForTests>.describeFun(
    specPair: SpecPair<T>,
    setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
) = describe("fun `${specPair.name}`") { setup(specPair.lambda) }

fun <T> TestFactoryBuilder<ExpectTestExecutableForTests>.itFun(
    specPair: SpecPair<T>,
    additionalDescription: String = "",
    setup: ExpectTestExecutableForTests.(T) -> Unit
) = it("fun `${specPair.name}`${if (additionalDescription.isEmpty()) "" else " -- $additionalDescription"}") {
    setup(specPair.lambda)
}

expect fun  TestFactoryBuilder<ExpectTestExecutableForTests>.describeIterable(
    iterableProvider: KProperty0<Function0<Iterable<*>>>,
    setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit
)
