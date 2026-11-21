package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionListLikeExpectation

@Suppress("FunctionName")
abstract class AbstractListExpectationsTest(
    private val getFeatureSpec: Feature1<List<Int>, Int, Int>,
    private val getSpec: Fun2<List<Int>, Int, Expect<Int>.() -> Unit>,
    private val getFeatureNullableSpec: Feature1<List<Int?>, Int, Int?>,
    private val getNullableSpec: Fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    private val list = listOf(1, 2, 3, 4)
    private val listNullable = listOf(1, null, 3, 4)

    private val indexOutOfBounds = DescriptionListLikeExpectation.INDEX_OUT_OF_BOUNDS.getDefault()

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            getFeatureSpec.forSubjectLessTest(1),
            getSpec.forSubjectLessTest(1) { toEqual(1) }
        ),
        SubjectLessTestData(
            getFeatureNullableSpec.forSubjectLessTest(1),
            getNullableSpec.forSubjectLessTest(1) { toEqual(null) },
            groupPrefix = "[nullable Element]"
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            list,
            getSpec.forExpectationCreatorTest("$toEqualDescr: 2", 1) { toEqual(2) }
        ),
        ExpectationCreatorTestData<List<Int?>>(
            list,
            getNullableSpec.forExpectationCreatorTest("$toEqualDescr: 2", 1) { toEqual(2) },
            groupPrefix = "[nullable Element]"
        )
    )

    @TestFactory
    fun get__list_1_to_4() = testFactoryForFeatureNonFeature(
        getFeatureSpec, getSpec
    ) { name, getFun, hasExtraHint ->
        it("$name - can perform sub-assertion on existing index") {
            expect(list).getFun(0) { toEqual(1) }
        }
        it("$name - non-existing index throws" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect(list).getFun(4) { toEqual(3) }
            }.toThrow<AssertionError> {
                messageToContain("get(4): $indexOutOfBounds")
                if (hasExtraHint) messageToContain("$toEqualDescr: 3")
            }
        }
    }

    @TestFactory
    fun get_nullable__list_1_to_4() = testFactoryForFeatureNonFeature(
        getFeatureNullableSpec, getNullableSpec
    ) { name, getFun, hasExtraHint ->
        it("$name - can perform sub-assertion on existing index") {
            expect<List<Int?>>(list).getFun(0) { toEqual(1) }
        }
        it("$name - non-existing index throws" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<List<Int?>>(list).getFun(4) { toEqual(3) }
            }.toThrow<AssertionError> {
                messageToContain("get(4): $indexOutOfBounds")
                if (hasExtraHint) messageToContain("$toEqualDescr: 3")
            }
        }
        it("$name - can perform sub-assertion on existing index with value null") {
            expect(listNullable).getFun(1) { toEqual(null) }
        }
    }
}
