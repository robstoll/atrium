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
    isSuccessFeature: Feature0<Result<Int>, Int>,
    isSuccess: Fun1<Result<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({


    val result: Result<Int> = Result.success(1)

    include(object : SubjectLessSpec<Result<Int>>(describePrefix,
        isSuccessFeature.forSubjectLess().adjustName { "$it feature" },
        isSuccess.forSubjectLess() { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        describePrefix, result,
        isSuccess.forAssertionCreatorSpec("$toBeDescr: 2" ) { toBe(2) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    val fluent = expect(result)

    val illegalStateException = DescriptionResultAssertion.ILLEGAL_STATE_EXCEPTION.getDefault()

    describeFun("${isSuccessFeature.name} feature") {
        val isSuccessFeature = isSuccessFeature.lambda
        context("result $result") {
            it("can perform sub-assertion result") {
                fluent.isSuccessFeature().toBe(1)
            }
            it("value") {
                expect {
                    fluent.isSuccessFeature().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("isSuccess(): $illegalStateException")
                }
            }
        }
    }

    describeFun(isSuccess.name) {
        val isSuccessFun = isSuccess.lambda
        context("result $result") {
            it("can perform sub-assertion on result") {
                fluent.isSuccessFun() { toBe(1) }
            }
            it("value") {
                expect {
                    fluent.isSuccessFun() { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("isSuccess(): $illegalStateException", "$toBeDescr: 3")
                }
            }
        }
    }

})
