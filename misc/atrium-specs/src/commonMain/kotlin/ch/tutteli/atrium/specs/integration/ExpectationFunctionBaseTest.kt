package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.*
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.testfactories.PlatformTestNodeContainer
import ch.tutteli.atrium.testfactories.PlatformTestNode
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.forElementAndForEachIn

expect abstract class ExpectationFunctionBaseTest() {

    protected fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit,
    ): PlatformTestNodeContainer<PlatformTestNode>

    protected fun testFactory(
        specPair: SpecPair<*>,
        otherSpecPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
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

    protected fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit,
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
            this.apply(subjectLessTestSetup(e, oE))
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
            this.apply(expectationCreatorTestSetup(data.subject, e, oE))
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

