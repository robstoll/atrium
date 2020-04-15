package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.util.*

abstract class OptionalAssertionsSpec(
    isEmpty: Fun0<Optional<Int>>,
    isPresentFeature: Feature0<Optional<Int>, Int>,
    isPresent: Fun1<Optional<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Optional<Int>>(
        "$describePrefix[Path] ",
        isEmpty.forSubjectLess(),
        isPresentFeature.forSubjectLess().adjustName { "$it feature" },
        isPresent.forSubjectLess { toBe(1) }
    ) {})
    include(object : AssertionCreatorSpec<Optional<Int>>(
        describePrefix, Optional.of(2),
        isPresent.forAssertionCreatorSpec("$toBeDescr: 2") { toBe(2) }
    ) {})


    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(isEmpty, isPresentFeature, isPresent) {
        val isEmptyFun = isEmpty.lambda
        val isPresentFunctions = unifySignatures(isPresentFeature, isPresent)

        val emptyValue = Optional.empty<Int>()
        context("$emptyValue") {
            it("${isEmpty.name} - does not throw") {
                expect(emptyValue).isEmptyFun()
            }
            isPresentFunctions.forEach { (name, isPresentFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect(emptyValue).isPresentFun { toBe(2) }
                    }.toThrow<AssertionError> {
                        messageContains(DescriptionOptionalAssertion.IS_NOT_PRESENT.getDefault())
                        if (hasExtraHint) messageContains("$toBeDescr: 2")
                    }
                }
            }
        }

        val presentValue: Optional<Int> = Optional.of(2)
        context("$presentValue") {
            it("${isEmpty.name} - throws an AssertionError") {
                expect {
                    expect(presentValue).isEmptyFun()
                }.toThrow<AssertionError> {
                    messageContains("$isDescr: ${DescriptionOptionalAssertion.EMPTY.getDefault()}")
                }
            }

            isPresentFunctions.forEach { (name, isPresentFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(presentValue).isPresentFun { toBe(2) }
                }
            }
        }
    }
})
