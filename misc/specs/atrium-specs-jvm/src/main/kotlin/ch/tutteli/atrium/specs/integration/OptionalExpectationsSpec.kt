package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.util.*

abstract class OptionalExpectationsSpec(
    toBeEmpty: Fun0<Optional<Int>>,
    toBePresentFeature: Feature0<Optional<Int>, Int>,
    toBePresent: Fun1<Optional<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Optional<Int>>(
        "$describePrefix[Path] ",
        toBeEmpty.forSubjectLess(),
        toBePresentFeature.forSubjectLess().adjustName { "$it feature" },
        toBePresent.forSubjectLess { toEqual(1) }
    ) {})
    include(object : AssertionCreatorSpec<Optional<Int>>(
        describePrefix, Optional.of(2),
        toBePresent.forAssertionCreatorSpec("$toEqualDescr: 2") { toEqual(2) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(toBeEmpty, toBePresentFeature, toBePresent) {
        val toBeEmptyFun = toBeEmpty.lambda
        val toBePresentFunctions = unifySignatures(toBePresentFeature, toBePresent)

        val emptyValue = Optional.empty<Int>()
        context("$emptyValue") {
            it("${toBeEmpty.name} - does not throw") {
                expect(emptyValue).toBeEmptyFun()
            }
            toBePresentFunctions.forEach { (name, toBePresentFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect(emptyValue).toBePresentFun { toEqual(2) }
                    }.toThrow<AssertionError> {
                        messageToContain(DescriptionOptionalAssertion.IS_NOT_PRESENT.getDefault())
                        if (hasExtraHint) messageToContain("$toEqualDescr: 2")
                    }
                }
            }
        }

        val presentValue: Optional<Int> = Optional.of(2)
        context("$presentValue") {
            it("${toBeEmpty.name} - throws an AssertionError") {
                expect {
                    expect(presentValue).toBeEmptyFun()
                }.toThrow<AssertionError> {
                    messageToContain("$isDescr: ${DescriptionOptionalAssertion.EMPTY.getDefault()}")
                }
            }

            toBePresentFunctions.forEach { (name, toBePresentFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(presentValue).toBePresentFun { toEqual(2) }
                }
            }
        }
    }
})
