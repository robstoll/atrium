package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.math.exp

abstract class IterableFeatureAssertionsSpec(
    minFeature: Feature0<Iterable<Int>,Int>,
    min: Fun1<Iterable<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterable<Int>>(describePrefix,
        minFeature.forSubjectLess().adjustName { "$it feature" },
        min.forSubjectLess { isGreaterThan(-100) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Int>>(
        describePrefix, listOf(-20,20,0),
        min.forAssertionCreatorSpec("$toBeDescr: -20") { toBe(-20) }
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
})
