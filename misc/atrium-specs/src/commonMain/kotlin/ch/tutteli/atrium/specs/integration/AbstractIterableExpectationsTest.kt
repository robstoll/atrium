package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation


@Suppress("FunctionName")
abstract class AbstractIterableExpectationsTest(
    private val toHaveElementsSpec: Fun0<Iterable<Int>>,
    private val notToHaveElementsSpec: Fun0<Iterable<Int>>,
    private val minFeatureSpec: Feature0<Iterable<Int>, Int>,
    private val minSpec: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    private val maxFeatureSpec: Feature0<Iterable<Int>, Int>,
    private val maxSpec: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    private val toHaveElementsAndNoDuplicatesSpec: Fun0<Iterable<Int>>,
    private val lastFeatureSpec: Feature0<Iterable<Int>, Int>,
    private val lastSpec: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toHaveElementsSpec.forSubjectLessTest(),
        notToHaveElementsSpec.forSubjectLessTest(),
        minFeatureSpec.forSubjectLessTest(),
        minSpec.forSubjectLessTest { toBeGreaterThan(-100) },
        maxFeatureSpec.forSubjectLessTest(),
        maxSpec.forSubjectLessTest { toEqual(1) },
        toHaveElementsAndNoDuplicatesSpec.forSubjectLessTest(),
        lastFeatureSpec.forSubjectLessTest(),
        lastSpec.forSubjectLessTest { toEqual(1) }
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        listOf(-20, 20, 0),
        minSpec.forExpectationCreatorTest("$toEqualDescr: -20") { toEqual(-20) },
        maxSpec.forExpectationCreatorTest("$toEqualDescr: 20") { toEqual(20) }
    )

    val toHaveDescr = DescriptionBasic.TO_HAVE.getDefault()
    val notToHaveDescr = DescriptionBasic.NOT_TO_HAVE.getDefault()
    val aNextElementDescr = DescriptionIterableLikeExpectation.A_NEXT_ELEMENT.getDefault()
    val duplicateElements = DescriptionIterableLikeExpectation.DUPLICATE_ELEMENTS.getDefault()

    val toHaveANextElement = "$toHaveDescr: $aNextElementDescr"

    @TestFactory
    fun toHaveElements() = testFactory(toHaveElementsSpec) { toHaveElementsFun ->
        it("does not throw if an iterable has next") {
            expect(listOf(1, 2) as Iterable<Int>).toHaveElementsFun()
        }

        it("throws an AssertionError if an iterable does not have next") {
            expect {
                expect(emptyList<Int>() as Iterable<Int>).toHaveElementsFun()
            }.toThrow<AssertionError> { messageToContain(toHaveANextElement) }
        }
    }

    @TestFactory
    fun notToHaveElements() = testFactory(notToHaveElementsSpec) { notToHaveElementsFun ->
        it("does not throw if an iterable has not next") {
            expect(emptyList<Int>() as Iterable<Int>).notToHaveElementsFun()
        }

        it("throws an AssertionError if an iterable has next element") {
            expect {
                expect(listOf(1, 2) as Iterable<Int>).notToHaveElementsFun()
            }.toThrow<AssertionError> { messageToContain("$notToHaveDescr: $aNextElementDescr") }
        }
    }

    val iterableWith4And3: () -> Iterable<Int> = { listOf(4, 3) }

    @TestFactory
    fun min() = testFactoryForFeatureNonFeature(minFeatureSpec, minSpec) { name, minFun, _ ->
        describeIterable(::iterableWith4And3) {
            it("$name - ${toBeGreaterThanDescr}(2) holds") {
                expect(iterableWith4And3()).minFun { toBeGreaterThan(2) }
            }
            it("$name - ${toBeLessThanDescr}(2) throws") {
                expect {
                    expect(iterableWith4And3()).minFun { toBeLessThan(2) }
                }.toThrow<AssertionError> {
                    messageToContain("min(): 3")
                }
            }
        }
    }

    @TestFactory
    fun max() = testFactoryForFeatureNonFeature(maxFeatureSpec, maxSpec) { name, maxFun, _ ->
        describeIterable(::iterableWith4And3) {
            it("$name - $toEqualDescr(4) holds") {
                expect(iterableWith4And3()).maxFun { toEqual(4) }
            }
            it("$name - $toEqualDescr(3) fails") {
                expect {
                    expect(iterableWith4And3()).maxFun { toEqual(3) }
                }.toThrow<AssertionError> {
                    messageToContain("max(): 4")
                }
            }
        }
    }

    @TestFactory
    fun min_max__empty_list() = testFactory(minFeatureSpec, minSpec, maxFeatureSpec, maxSpec) {
        val functions = unifySignatures(minFeatureSpec, minSpec) + unifySignatures(maxFeatureSpec, maxSpec)

        describe("empty list") {
            val emptyIterable = expect(emptyList<Int>() as Iterable<Int>)
            val noElementsDescr = DescriptionIterableLikeExpectation.NO_ELEMENTS.getDefault()

            functions.forEach { (name, minFun, hasExtraHint) ->
                it("$name - fails warning about empty iterable" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        emptyIterable.minFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noElementsDescr)
                            if (hasExtraHint) toContain("$toEqualDescr: 1")
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun toHaveElementsAndNoDuplicates() = testFactory(
        toHaveElementsAndNoDuplicatesSpec
    ) { toHaveElementsAndNoDuplicatesFun ->
        describe("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(emptyList<Int>() as Iterable<Int>).toHaveElementsAndNoDuplicatesFun()
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            toHaveANextElement,
                            "$notToHaveDescr: $duplicateElements"
                        )
                    }
                }
            }
        }

        describe("list without duplicates") {
            it("happy case") {
                expect(listOf(1, 2) as Iterable<Int>).toHaveElementsAndNoDuplicatesFun()
            }
        }

        describe("list with duplicates") {
            it("throws AssertionError with details of duplicate indices") {
                fun index(i: Int, element: Int) =
                    DescriptionIterableLikeExpectation.INDEX.getDefault().format("$i: $element")

                fun duplicatedBy(vararg elements: Int) =
                    DescriptionIterableLikeExpectation.DUPLICATED_BY.getDefault().format(elements.joinToString(", "))

                val input = listOf(1, 2, 1, 2, 3, 4, 4, 4).asIterable()

                expect {
                    expect(input).toHaveElementsAndNoDuplicatesFun()
                }.toThrow<AssertionError> {
                    message {
                        toContain("$notToHaveDescr: $duplicateElements")
                        toContain(index(0, 1), index(1, 2), index(5, 4))
                        toContain(duplicatedBy(2), duplicatedBy(3), duplicatedBy(6, 7))
                    }
                }
            }
        }
    }

    val oneThreeFour: () -> Iterable<Int> = { listOf(1, 3, 4) }

    @TestFactory
    fun last() = testFactoryForFeatureNonFeature(lastFeatureSpec, lastSpec) { name, lastFun, hasExtraHint ->

        describeIterable(::oneThreeFour) {
            it("$name - can perform sub-assertion on last element with value") {
                expect(oneThreeFour()).lastFun { toEqual(4) }
            }
        }

        val listIsEmptyDescr = DescriptionIterableLikeExpectation.NO_ELEMENTS.getDefault()

        describe("empty list") {
            it("$name - empty list throws" + showsSubExpectationIf(hasExtraHint)) {
                expect {
                    expect(emptyList<Int>() as Iterable<Int>).lastFun { toEqual(3) }
                }.toThrow<AssertionError> {
                    messageToContain("last(): $listIsEmptyDescr")
                    if (hasExtraHint) messageToContain("$toEqualDescr: 3")
                }
            }
        }
    }
}
