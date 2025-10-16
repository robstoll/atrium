package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class AbstractIterableExpectationsTest(
    toHaveElements: Fun0<Iterable<Int>>,
    notToHaveElements: Fun0<Iterable<Int>>,
    minFeature: Feature0<Iterable<Int>, Int>,
    min: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    maxFeature: Feature0<Iterable<Int>, Int>,
    max: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    toHaveElementsAndNoDuplicates: Fun0<Iterable<Int>>,
    lastFeature: Feature0<Iterable<Int>, Int>,
    last: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterable<Int>>(
        describePrefix,
        toHaveElements.forSubjectLessTest(),
        notToHaveElements.forSubjectLessTest(),
        minFeature.forSubjectLessTest(),
        min.forSubjectLessTest { toBeGreaterThan(-100) },
        maxFeature.forSubjectLessTest(),
        max.forSubjectLessTest { toEqual(1) },
        toHaveElementsAndNoDuplicates.forSubjectLessTest()
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Int>>(
        describePrefix, listOf(-20, 20, 0),
        min.forExpectationCreatorTest("$toEqualDescr: -20") { toEqual(-20) },
        max.forExpectationCreatorTest("$toEqualDescr: 20") { toEqual(20) }
    ) {})

    include(object : SubjectLessSpec<Iterable<Int>>(describePrefix,
        lastFeature.forSubjectLessTest(),
        last.forSubjectLessTest { toEqual(1) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val toHaveDescr = DescriptionBasic.TO_HAVE.getDefault()
    val notToHaveDescr = DescriptionBasic.NOT_TO_HAVE.getDefault()
    val aNextElementDescr = DescriptionIterableLikeExpectation.A_NEXT_ELEMENT.getDefault()
    val duplicateElements = DescriptionIterableLikeExpectation.DUPLICATE_ELEMENTS.getDefault()

    val toHaveANextElement = "$toHaveDescr: $aNextElementDescr"

    describeFun(toHaveElements) {
        val toHaveElementsFun = toHaveElements.lambda

        it("does not throw if an iterable has next") {
            expect(listOf(1, 2) as Iterable<Int>).toHaveElementsFun()
        }

        it("throws an AssertionError if an iterable does not have next") {
            expect {
                expect(emptyList<Int>() as Iterable<Int>).toHaveElementsFun()
            }.toThrow<AssertionError> { messageToContain(toHaveANextElement) }
        }
    }

    describeFun(notToHaveElements) {
        val notToHaveElementsFun = notToHaveElements.lambda

        it("does not throw if an iterable has not next") {
            expect(emptyList<Int>() as Iterable<Int>).notToHaveElementsFun()
        }

        it("throws an AssertionError if an iterable has next element") {
            expect {
                expect(listOf(1, 2) as Iterable<Int>).notToHaveElementsFun()
            }.toThrow<AssertionError> { messageToContain("$notToHaveDescr: $aNextElementDescr") }
        }
    }

    describeFun(minFeature, min, maxFeature, max) {
        val minFunctions = unifySignatures(minFeature, min)
        val maxFunctions = unifySignatures(maxFeature, max)

        context("list with 4 and 3") {
            val iterableWith4And3 = listOf(4, 3) as Iterable<Int>
            minFunctions.forEach { (name, minFun, _) ->
                it("$name - is greater than 2 holds") {
                    expect(iterableWith4And3).minFun { toBeGreaterThan(2) }
                }
                it("$name - is less than 2 fails") {
                    expect {
                        expect(iterableWith4And3).minFun { toBeLessThan(2) }
                    }.toThrow<AssertionError> {
                        messageToContain("min(): 3")
                    }
                }
            }
            maxFunctions.forEach { (name, maxFun, _) ->
                it("$name - toBe(4) holds") {
                    expect(iterableWith4And3).maxFun { toEqual(4) }
                }
                it("$name - $toEqualDescr(3) fails") {
                    expect {
                        expect(iterableWith4And3).maxFun { toEqual(3) }
                    }.toThrow<AssertionError> {
                        messageToContain("max(): 4")
                    }
                }
            }
        }

        context("empty list") {
            val emptyIterable = expect(emptyList<Int>() as Iterable<Int>)
            val noElementsDescr = DescriptionIterableLikeExpectation.NO_ELEMENTS.getDefault()

            minFunctions.forEach { (name, minFun, _) ->
                it("$name - fails warning about empty iterable") {
                    expect {
                        emptyIterable.minFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        messageToContain(noElementsDescr)
                    }
                }
            }
            maxFunctions.forEach { (name, maxFun, _) ->
                it("$name - fails warning about empty iterable") {
                    expect {
                        emptyIterable.maxFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        messageToContain(noElementsDescr)
                    }
                }
            }
        }
    }

    describeFun(toHaveElementsAndNoDuplicates) {
        val toHaveElementsAndNoDuplicatesFun = toHaveElementsAndNoDuplicates.lambda

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
                fun index(i: Int, element: Int) = DescriptionIterableLikeExpectation.INDEX.getDefault().format("$i: $element")
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

    val listNullable = listOf(1, 3, 4) as Iterable<Int>
    val fluentNullable = expect(listNullable)

    describeFun(lastFeature, last) {
        val lastFunctions = unifySignatures(lastFeature, last)
        context("list $listNullable") {
            lastFunctions.forEach { (name, lastFun, _) ->
                it("$name - can perform sub-assertion on last element with value") {
                    fluentNullable.lastFun { toEqual(4) }
                }
            }
        }
    }

    val emptyList = emptyList<Int>() as Iterable<Int>
    val fluentEmptyList = expect(emptyList)

    val listIsEmptyDescr = DescriptionIterableLikeExpectation.NO_ELEMENTS.getDefault()

    describeFun(lastFeature, last) {
        val lastFunctions = unifySignatures(lastFeature, last)
        context("list $emptyList") {
            lastFunctions.forEach { (name, lastFun, hasExtraHint) ->
                it("$name - empty list throws" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        fluentEmptyList.lastFun { toEqual(3) }
                    }.toThrow<AssertionError> {
                        messageToContain("last(): $listIsEmptyDescr")
                        if (hasExtraHint) messageToContain("$toEqualDescr: 3")
                    }
                }
            }
        }
    }
})
