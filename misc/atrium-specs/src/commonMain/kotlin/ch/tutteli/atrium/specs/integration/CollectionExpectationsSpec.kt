package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof.EMPTY
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class CollectionExpectationsSpec(
    isEmpty: Fun0<Collection<Int>>,
    isNotEmpty: Fun0<Collection<Int>>,
    sizeFeature: Feature0<Collection<Int>, Int>,
    size: Fun1<Collection<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Collection<Int>>(
        describePrefix,
        isEmpty.forSubjectLess(),
        isNotEmpty.forSubjectLess(),
        sizeFeature.forSubjectLess().withFeatureSuffix(),
        size.forSubjectLess { toBeGreaterThan(2) }
    ) {})

    include(object : AssertionCreatorSpec<Collection<Int>>(
        describePrefix, listOf(999),
        size.forAssertionCreatorSpec("$toEqualDescr\\s+: 1") { toEqual(1) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(isEmpty, isNotEmpty) {
        val isEmptyFun = isEmpty.lambda
        val isNotEmptyFun = isNotEmpty.lambda

        context("collection is empty") {
            it("${isEmpty.name} - does not throw") {
                expect(emptyList<Int>() as Collection<Int>).isEmptyFun()
            }
            it("${isNotEmpty.name} - throws an AssertionError") {
                expect {
                    expect(emptyList<Int>() as Collection<Int>).isNotEmptyFun()
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("[]")
                        toContainNotToBeDescr(EMPTY.string)
                    }
                }
            }
        }

        context("collection is not empty") {
            it("${isEmpty.name} - throws an AssertionError") {
                expect {
                    expect(listOf(1, 2) as Collection<Int>).isEmptyFun()
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("[1, 2]")
                        toContainToBeDescr(EMPTY)
                    }
                }
            }
            it("${isNotEmpty.name} - does not throw") {
                expect(listOf(1) as Collection<Int>).isNotEmptyFun()
            }
        }
    }

    describeFun(sizeFeature, size) {
        val sizeFunctions = unifySignatures(sizeFeature, size)

        context("list with two entries") {
            sizeFunctions.forEach { (name, sizeFun, _) ->
                it("$name - is greater than 1 holds") {
                    expect(listOf(1, 2) as Collection<Int>).sizeFun { toBeGreaterThan(1) }
                }
                it("$name - is less than 1 fails") {
                    expect {
                        expect(listOf(1, 2) as Collection<Int>).sizeFun { toBeLessThan(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(DescriptionCollectionProof.SIZE, 2)
                        }
                    }
                }
            }
        }
    }

})
