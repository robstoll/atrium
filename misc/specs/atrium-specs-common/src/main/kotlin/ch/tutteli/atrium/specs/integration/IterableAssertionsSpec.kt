package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IterableAssertionsSpec(
    hasNext: Fun0<Iterable<Int>>,
    hasNotNext: Fun0<Iterable<Int>>,
    minFeature: Feature0<Iterable<Int>,Int>,
    min: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    maxFeature: Feature0<Iterable<Int>, Int>,
    max: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterable<Int>>(
        describePrefix,
        hasNext.forSubjectLess(),
        minFeature.forSubjectLess(),
        min.forSubjectLess { isGreaterThan(-100) },
        maxFeature.forSubjectLess(),
        max.forSubjectLess { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Int>>(
        describePrefix, listOf(-20,20,0),
        min.forAssertionCreatorSpec("$toBeDescr: -20") { toBe(-20) },
        max.forAssertionCreatorSpec("$toBeDescr: 20") { toBe(20) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val hasDescriptionBasic = DescriptionBasic.HAS.getDefault()
    val hasNotDescriptionBasic = DescriptionBasic.HAS_NOT.getDefault()
    val nextElement = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()

    describeFun(hasNext) {
        val hasNextFun = hasNext.lambda

        it("does not throw if an iterable has next") {
            expect(listOf(1, 2) as Iterable<Int>).hasNextFun()
        }

        it("throws an AssertionError if an iterable does not have next") {
            expect {
                expect(listOf<Int>() as Iterable<Int>).hasNextFun()
            }.toThrow<AssertionError> { messageContains("$hasDescriptionBasic: $nextElement") }
        }
    }

    describeFun(hasNotNext) {
        val hasNotNextFun = hasNotNext.lambda

        it("does not throw if an iterable has not next") {
            expect(listOf<Int>() as Iterable<Int>).hasNotNextFun()
        }

        it("throws an AssertionError if an iterable has next element") {
            expect {
                expect(listOf(1, 2) as Iterable<Int>).hasNotNextFun()
            }.toThrow<AssertionError> { messageContains("$hasNotDescriptionBasic: $nextElement") }
        }
    }

    describeFun(minFeature, min, maxFeature, max) {
        val minFunctions = unifySignatures(minFeature, min)
        val maxFunctions = unifySignatures(maxFeature, max)

        context("list with 4 and 3") {
            val fluent = expect(listOf(4, 3) as Iterable<Int>)
            minFunctions.forEach { (name, minFun, _) ->
                it("$name - is greater than 2 holds") {
                    fluent.minFun { isGreaterThan(2) }
                }
                it("$name - is less than 2 fails") {
                    expect {
                        fluent.minFun { isLessThan(2) }
                    }.toThrow<AssertionError> {
                        messageContains("min(): 3")
                    }
                }
            }
            maxFunctions.forEach { (name, maxFun, _) ->
                it("$name - toBe(4) holds") {
                    fluent.maxFun { toBe(4) }
                }
                it("$name - toBe(3) fails") {
                    expect {
                        fluent.maxFun { toBe(3) }
                    }.toThrow<AssertionError> {
                        messageContains("max(): 4")
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
                        emptyIterable.minFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains(noElementsDescr)
                    }
                }
            }
            maxFunctions.forEach { (name, maxFun, _) ->
                it("$name - fails warning about empty iterable") {
                    expect {
                        emptyIterable.maxFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains(noElementsDescr)
                    }
                }
            }
        }
    }

})
