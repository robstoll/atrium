package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IteratorExpectationsSpec(
    toHaveNext: Fun0<Iterator<Int>>,
    notToHaveNext: Fun0<Iterator<Int>>,
    nextFeature: Feature0<Iterator<Int>, Int>,
    next: Fun1<Iterator<Int>, Expect<Int>.() -> Unit>,
    nextFeatureNullable: Feature0<Iterator<Int?>, Int?>,
    nextNullable: Fun1<Iterator<Int?>, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({
    val list = listOf(1, 2, 3, 4)

    include(object : SubjectLessSpec<Iterator<Int>>(
        describePrefix,
        toHaveNext.forSubjectLess()
    ) {})

    include(object : AssertionCreatorSpec<Iterator<Int>>(
        describePrefix, list.iterator(),
        next.forAssertionCreatorSpec("$toEqualDescr\\s+: 1") { toEqual(1) }
    ) {})

    include(object : AssertionCreatorSpec<Iterator<Int?>>(
        describePrefix, list.iterator(),
        nextNullable.forAssertionCreatorSpec("$toEqualDescr\\s+: 1") { toEqual(1) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val aNextElement = DescriptionIterableLikeProof.A_NEXT_ELEMENT.string
    val sizeExceededDescr = DescriptionIterableLikeProof.SIZE_EXCEEDED.string

    describeFun(toHaveNext) {
        val toHaveNextFun = toHaveNext.lambda

        it("does not throw if an iterator has next") {
            expect(listOf(1, 2).iterator()).toHaveNextFun()
        }

        it("throws an AssertionError if an iterator does not have next") {
            expect {
                expect(emptyList<Int>().iterator()).toHaveNextFun()
            }.toThrow<AssertionError> {
                message { toContainDescr(DescriptionBasic.TO_HAVE, aNextElement) }
            }
        }
    }

    describeFun(notToHaveNext) {
        val notToHaveNextFun = notToHaveNext.lambda

        it("does not throw if an iterator does not have next") {
            expect {
                expect(emptyList<Int>().iterator()).notToHaveNextFun()
            }
        }

        it("throws an AssertionError if an iterator has next") {
            expect {
                expect(listOf(1, 2).iterator()).notToHaveNextFun()
            }.toThrow<AssertionError> {
                message { toContainDescr(DescriptionBasic.NOT_TO_HAVE, aNextElement) }
            }
        }
    }

    describeFun(nextFeature, next, nextFeatureNullable, nextNullable) {
        val nextFunctions = uncheckedToNonNullable(
            unifySignatures(nextFeature, next),
            unifySignatures(nextFeatureNullable, nextNullable)
        )

        nextFunctions.forEach { (name, nextFun, hasExtraHint) ->
            it("$name - can perform sub-assertion if an iterator has next") {
                expect {
                    expect(listOf(1).iterator()).nextFun { toEqual(1) }
                }
            }

            it("$name - throws an AssertionError if an iterator does not have next") {
                expect {
                    expect(emptyList<Int>().iterator()).nextFun { toEqual(1) }
                }.toThrow<AssertionError> {
                    message {
                        toContain(sizeExceededDescr)
                        if (hasExtraHint) toContainToEqualDescr(1)
                    }
                }
            }
        }
    }

    describeFun(nextFeatureNullable, nextNullable) {
        val nextFunctions = unifySignatures(nextFeatureNullable, nextNullable)

        nextFunctions.forEach { (name, nextFun, _) ->
            it("$name - can perform sub-assertion if next value is null") {
                val iterator = listOf<Int?>(null).iterator()
                expect(iterator).nextFun { toEqual(null) }
            }
        }
    }

})
