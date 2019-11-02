package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionResultAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ResultFeatureAssertionsSpec(
    getFeature: Feature1<Result<Int>, Int, Int>,
    isSuccess: Fun2<Result<Int>, Int, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({


    val result: Result<Int> = Result.success(1)

    include(object : SubjectLessSpec<Result<Int>>(describePrefix,
        getFeature.forSubjectLess(1).adjustName { "$it feature" },
        isSuccess.forSubjectLess(1) { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        describePrefix, result,
        isSuccess.forAssertionCreatorSpec("$toBeDescr: 2", 1) { toBe(2) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    val fluent = expect(result)

    val illegalStateException = DescriptionResultAssertion.ILLEGAL_STATE_EXCEPTION.getDefault()

    describeFun("${getFeature.name} feature") {
        val getFun = getFeature.lambda
        context("result $result") {
            it("can perform sub-assertion result") {
                fluent.getFun(0).toBe(1)
            }
            it("value") {
                expect {
                    fluent.getFun(4).toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("get(4): $illegalStateException")
                }
            }
        }
    }

    describeFun(isSuccess.name) {
        val getFun = isSuccess.lambda
        context("result $result") {
            it("can perform sub-assertion on result") {
                fluent.getFun(0) { toBe(1) }
            }
            it("value") {
                expect {
                    fluent.getFun(4) { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(4): $illegalStateException", "$toBeDescr: 3")
                }
            }
        }
    }

})
