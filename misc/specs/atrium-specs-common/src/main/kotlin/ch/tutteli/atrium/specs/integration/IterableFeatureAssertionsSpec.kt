package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.AssertionCreatorSpec
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.adjustName
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.forAssertionCreatorSpec
import ch.tutteli.atrium.specs.forSubjectLess
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IterableFeatureAssertionsSpec(
    minFeature: Feature0<Iterable<Int>,Int>,
    min: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    maxFeature: Feature0<Iterable<Int>, Int>,
    max: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterable<Int>>(describePrefix,
        minFeature.forSubjectLess().adjustName { "$it feature" },
        min.forSubjectLess { isGreaterThan(-100) },
        maxFeature.forSubjectLess().adjustName { "$it feature" },
        max.forSubjectLess { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Int>>(
        describePrefix, listOf(-20,20,0),
        min.forAssertionCreatorSpec("$toBeDescr: -20") { toBe(-20) },
        max.forAssertionCreatorSpec("$toBeDescr: 20") { toBe(20) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(listOf(4, 3) as Iterable<Int>)
    val empty = expect (emptyList<Int>() as Iterable<Int>)

    describeFun("val ${minFeature.name}") {
        val minVal = minFeature.lambda

        context("list with 4 and 3") {
            it("toBe(3) holds") {
                fluent.minVal().toBe(3)
            }
            it("toBe(5) fails") {
                expect {
                    fluent.minVal().toBe(5)
                }.toThrow<AssertionError> {
                    messageContains("min(): 3")
                }
            }
        }

        context("empty list") {
            it("toBe(3) fails") {
                expect {
                    empty.minVal().toBe(3)
                }.toThrow<AssertionError> {
                    messageContains(DescriptionIterableAssertion.NO_ELEMENTS.getDefault())
                }
            }
        }
    }

    describeFun("fun ${min.name}") {
        val minFun = min.lambda

        context("list with two entries") {
            it("is greater than 2 holds") {
                fluent.minFun { isGreaterThan(2) }
            }
            it("is less than 2 fails") {
                expect {
                    fluent.minFun { isLessThan(2) }
                }.toThrow<AssertionError> {
                    messageContains("min(): 3")
                }
            }
        }
    }

    describeFun("val ${maxFeature.name}") {
        val maxVal = max.lambda
        checkMax { assertion -> maxVal(assertion) }
    }

    describeFun("fun ${max.name}") {
        val maxFun = maxFeature.lambda
        checkMax { assert -> maxFun().assert() }
    }
})


private fun Suite.checkMax(testMax: Expect<Iterable<Int>>.(Expect<Int>.() -> Unit) -> Unit) {
    val emptyIterable = expect(emptyList<Int>() as Iterable<Int>)

    val filledIterable = expect(listOf(1, 2) as Iterable<Int>)

    context("list with 1 and 2") {
        it("toBe(2) holds") {
            filledIterable.testMax { toBe(2) }
        }
        it("toBe(1) fails") {
            expect {
                filledIterable.testMax { toBe(1) }
            }.toThrow<AssertionError> {
                messageContains("max(): 2")
            }
        }
    }

    context("empty list") {
        it("fails warning about empty iterable") {
            expect {
                emptyIterable.testMax { toBe(1) }
            }.toThrow<AssertionError> {
                messageContains("cannot be determined, empty Iterable")
            }
        }
    }
}
