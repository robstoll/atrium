package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
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
