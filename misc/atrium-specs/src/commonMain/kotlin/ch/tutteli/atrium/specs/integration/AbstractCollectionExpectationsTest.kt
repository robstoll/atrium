package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation

@Suppress("FunctionName")
abstract class AbstractCollectionExpectationsTest(
    private val toBeEmptySpec: Fun0<Collection<Int>>,
    private val notToBeEmptySpec: Fun0<Collection<Int>>,
    private val sizeFeatureSpec: Feature0<Collection<Int>, Int>,
    private val sizeSpec: Fun1<Collection<Int>, Expect<Int>.() -> Unit>
) : ExpectationFunctionBaseTest() {


    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toBeEmptySpec.forSubjectLessTest(),
        notToBeEmptySpec.forSubjectLessTest(),
        sizeFeatureSpec.forSubjectLessTest().withFeatureSuffix(),
        sizeSpec.forSubjectLessTest { toBeLessThan(2) }
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        listOf(999),
        sizeSpec.forExpectationCreatorTest("$toEqualDescr: 1")
        { toEqual(1) }
    )

    val emptyDescr = DescriptionCollectionExpectation.EMPTY.getDefault()
    val sizeDescr = DescriptionCollectionExpectation.SIZE.getDefault()

    @TestFactory
    fun toBeEmpty_notToBeEmpty() = testFactory(toBeEmptySpec, notToBeEmptySpec) {
        describe("collection is empty") {
            it("${toBeEmptySpec.name} - does not throw") {
                expect(emptyList<Int>() as Collection<Int>).toBeEmpty()
            }
            it("${notToBeEmptySpec.name} - throws an AssertionError") {
                expect {
                    expect(emptyList<Int>() as Collection<Int>).notToBeEmpty()
                }.toThrow<AssertionError> { messageToContain("$notToBeDescr: $emptyDescr") }
            }
        }
        it("${toBeEmptySpec.name} - does not throw") {
            expect {
                expect(emptyList<Int>() as Collection<Int>).toBeEmpty()
            }
        }
        describe("collection is not empty") {
            it("${toBeEmptySpec.name} - throws an AssertionError") {
                expect {
                    expect(listOf(1, 2) as Collection<Int>).toBeEmpty()
                }.toThrow<AssertionError> { messageToContain("$toBeDescr: $emptyDescr") }
            }
            it("${notToBeEmptySpec.name} - does not throw") {
                expect(listOf(1) as Collection<Int>).notToBeEmpty()
            }
        }
    }

    @TestFactory
    fun size__list_with_two_entries() = testFactory(sizeFeatureSpec, sizeSpec) {
        val sizeFunctions = unifySignatures(sizeFeatureSpec, sizeSpec)
        sizeFunctions.forEach { (name, sizeFun, _) ->
            it("$name - is greater than 1 holds") {
                expect(listOf(1, 2) as Collection<Int>).sizeFun { toBeGreaterThan(1) }
            }
            it("$name - is less than 1 fails") {
                expect {
                    expect(listOf(1, 2) as Collection<Int>).sizeFun { toBeLessThan(1) }
                }.toThrow<AssertionError> {
                    messageToContain("$sizeDescr: 2")
                }
            }
        }
    }
}
