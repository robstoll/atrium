package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.integration.utils.*
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.testfactories.DynamicNodeContainer
import ch.tutteli.atrium.testfactories.DynamicNodeLike
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.forElementAndForEachIn

expect abstract class ExpectationFunctionBaseTest() {

    protected fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit,
    ): DynamicNodeContainer<DynamicNodeLike>

    protected fun testFactory(
        specPair: SpecPair<*>,
        otherSpecPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    ): DynamicNodeContainer<DynamicNodeLike>

    protected fun <SubjectT> subjectLessTestFactory(
        expectationCreator: SpecPair<Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String? = null
    ): DynamicNodeContainer<DynamicNodeLike>

    protected fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): DynamicNodeContainer<DynamicNodeLike>

    protected fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: ExpectationCreatorTriple<SubjectT>,
        vararg otherAssertionCreators: ExpectationCreatorTriple<SubjectT>,
        groupPrefix: String? = null
    ): DynamicNodeContainer<DynamicNodeLike>

    protected fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ): DynamicNodeContainer<DynamicNodeLike>

    protected fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit,
    ): DynamicNodeContainer<DynamicNodeLike>
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

