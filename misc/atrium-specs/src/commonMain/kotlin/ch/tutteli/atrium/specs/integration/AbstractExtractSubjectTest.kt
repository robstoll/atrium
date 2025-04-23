package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

@Suppress("FunctionName")
abstract class AbstractExtractSubjectTest(
    private val extractSubjectSpec: Fun2<Int, String?, Expect<Int>.(Int) -> Unit>,
    private val extractSubjectNullableSpec: Fun2<Int?, String?, Expect<Int?>.(Int?) -> Unit>,
    private val extractSubjectDefaultFailureDescription: String
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            1,
            ExpectationCreatorTriple(
                extractSubjectSpec.name, "$toEqualDescr: 1",
                { extractSubjectSpec.invoke(this, "failure description") { toEqual(1) } },
                { extractSubjectSpec.invoke(this, "failure description") {} }
            )
        ),
        ExpectationCreatorTestData(
            1 as Int?,
            ExpectationCreatorTriple(
                extractSubjectNullableSpec.name, "$toEqualDescr: 1",
                { extractSubjectNullableSpec.invoke(this, "failure description") { toEqual(1) } },
                { extractSubjectNullableSpec.invoke(this, "failure description") {} }
            ),
        )
    )

    @TestFactory
    fun subject_defined__extraction_successful() =
        nonNullableCases(extractSubjectSpec, extractSubjectNullableSpec) { extractSubjectFun ->
            expect(1).extractSubjectFun("failure desc irrelevant") { subject ->
                feature("extracted subject") { subject }.toEqual(1)
            }
        }

    @TestFactory
    fun subject_not_defined__shows_the_default_failure_description_if_null_passed() =
        nonNullableCases(extractSubjectSpec, extractSubjectNullableSpec) { extractSubjectFun ->
            expect {
                expect(null as Int?).notToEqualNull {
                    extractSubjectFun(null) { _ ->
                        // should not be evaluated
                        toEqual(1)
                    }
                }
            }.toThrow<AssertionError> {
                message {
                    toContain(extractSubjectDefaultFailureDescription)
                    notToContain("$toEqualDescr: 1")
                }
            }
        }

    @TestFactory
    fun subject_not_defined__shows_the_given_failure_description() =
        nonNullableCases(extractSubjectSpec, extractSubjectNullableSpec) { extractSubjectFun ->
            val failureDescription = "failure description shown in reporting"
            expect {
                expect(null as Int?).notToEqualNull {
                    extractSubjectFun(failureDescription) { _ ->
                        // should not be evaluated
                        toEqual(1)
                    }
                }
            }.toThrow<AssertionError> {
                message {
                    toContain(failureDescription)
                    notToContain("$toEqualDescr: 1")
                }
            }
        }

    @Test
    fun subject_null__extraction_also_successful() {
        val extractSubjectFun = extractSubjectNullableSpec.lambda
        expect(null as Int?).extractSubjectFun("failure desc irrelevant") { subject ->
            feature("extracted subject") { subject }.toEqual(null)
        }
    }
}
