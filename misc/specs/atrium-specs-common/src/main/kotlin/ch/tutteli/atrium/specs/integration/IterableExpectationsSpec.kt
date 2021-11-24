package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IterableExpectationsSpec(
    toHaveElements: Fun0<Iterable<Int>>,
    notToHaveElements: Fun0<Iterable<Int>>,
    minFeature: Feature0<Iterable<Int>, Int>,
    min: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    maxFeature: Feature0<Iterable<Int>, Int>,
    max: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    toHaveElementsAndNoDuplicates: Fun0<Iterable<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterable<Int>>(
        describePrefix,
        toHaveElements.forSubjectLess(),
        notToHaveElements.forSubjectLess(),
        minFeature.forSubjectLess(),
        min.forSubjectLess { toBeGreaterThan(-100) },
        maxFeature.forSubjectLess(),
        max.forSubjectLess { toEqual(1) },
        toHaveElementsAndNoDuplicates.forSubjectLess()
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Int>>(
        describePrefix, listOf(-20, 20, 0),
        min.forAssertionCreatorSpec("$toEqualDescr: -20") { toEqual(-20) },
        max.forAssertionCreatorSpec("$toEqualDescr: 20") { toEqual(20) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val hasDescr = DescriptionBasic.HAS.getDefault()
    val hasNotDescr = DescriptionBasic.HAS_NOT.getDefault()
    val nextElementDescr = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()
    val duplicateElements = DescriptionIterableAssertion.DUPLICATE_ELEMENTS.getDefault()

    val hasANextElement = "$hasDescr: $nextElementDescr"

    describeFun(toHaveElements) {
        val toHaveElementsFun = toHaveElements.lambda

        it("does not throw if an iterable has next") {
            expect(listOf(1, 2) as Iterable<Int>).toHaveElementsFun()
        }

        it("throws an AssertionError if an iterable does not have next") {
            expect {
                expect(listOf<Int>() as Iterable<Int>).toHaveElementsFun()
            }.toThrow<AssertionError> { messageToContain(hasANextElement) }
        }
    }

    describeFun(notToHaveElements) {
        val notToHaveElementsFun = notToHaveElements.lambda

        it("does not throw if an iterable has not next") {
            expect(listOf<Int>() as Iterable<Int>).notToHaveElementsFun()
        }

        it("throws an AssertionError if an iterable has next element") {
            expect {
                expect(listOf(1, 2) as Iterable<Int>).notToHaveElementsFun()
            }.toThrow<AssertionError> { messageToContain("$hasNotDescr: $nextElementDescr") }
        }
    }

    describeFun(minFeature, min, maxFeature, max) {
        val minFunctions = unifySignatures(minFeature, min)
        val maxFunctions = unifySignatures(maxFeature, max)

        context("list with 4 and 3") {
            val fluent = expect(listOf(4, 3) as Iterable<Int>)
            minFunctions.forEach { (name, minFun, _) ->
                it("$name - is greater than 2 holds") {
                    fluent.minFun { toBeGreaterThan(2) }
                }
                it("$name - is less than 2 fails") {
                    expect {
                        fluent.minFun { toBeLessThan(2) }
                    }.toThrow<AssertionError> {
                        messageToContain("min(): 3")
                    }
                }
            }
            maxFunctions.forEach { (name, maxFun, _) ->
                it("$name - toBe(4) holds") {
                    fluent.maxFun { toEqual(4) }
                }
                it("$name - $toEqualDescr(3) fails") {
                    expect {
                        fluent.maxFun { toEqual(3) }
                    }.toThrow<AssertionError> {
                        messageToContain("max(): 4")
                    }
                }
            }
        }

        context("empty list") {
            val emptyIterable = expect(emptyList<Int>() as Iterable<Int>)
            val noElementsDescr = DescriptionIterableAssertion.NO_ELEMENTS.getDefault()

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
                    expect(listOf<Int>() as Iterable<Int>).toHaveElementsAndNoDuplicatesFun()
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            hasANextElement,
                            "$hasNotDescr: $duplicateElements"
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
                fun index(i: Int, element: Int) = DescriptionIterableAssertion.INDEX.getDefault().format("$i: $element")
                fun duplicatedBy(vararg elements: Int) =
                    DescriptionIterableAssertion.DUPLICATED_BY.getDefault().format(elements.joinToString(", "))

                val input = listOf(1, 2, 1, 2, 3, 4, 4, 4).asIterable()

                expect {
                    expect(input).toHaveElementsAndNoDuplicatesFun()
                }.toThrow<AssertionError> {
                    message {
                        toContain("$hasNotDescr: $duplicateElements")
                        toContain(index(0, 1), index(1, 2), index(5, 4))
                        toContain(duplicatedBy(2), duplicatedBy(3), duplicatedBy(6, 7))
                    }
                }
            }
        }
    }
})
