package ch.tutteli.atrium.specs.integration.utils

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.notToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.ErrorMessages
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.forElementAndForEachIn

fun <SubjectT> expectationCreatorTestSetup(
    subject: SubjectT,
    expectationCreator: ExpectationCreatorTriple<SubjectT>,
    otherExpectationCreators: Array<out ExpectationCreatorTriple<SubjectT>>,
): TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit = {
    forElementAndForEachIn(expectationCreator, otherExpectationCreators) { (name, stringNotInMessage, lambdas) ->
        val (createExpectationOk, createExpectationFail) = lambdas
        describe("fun `$name`") {
            it("throws and only reports the lambda which was empty") {
                expect {
                    expect(subject)
                        .createExpectationOk()
                        .createExpectationFail()
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED.getDefault() + ": false",
                            ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION.getDefault(),
                            ErrorMessages.HINT_AT_LEAST_ONE_EXPECTATION_DEFINED.getDefault()
                        )
                        notToContain(stringNotInMessage)
                    }
                }
            }
        }
    }
}


class ExpectationCreatorTestData<SubjectT>(
    val subject: SubjectT,
    val expectationCreator: ExpectationCreatorTriple<SubjectT>,
    vararg val otherExpectationCreators: ExpectationCreatorTriple<SubjectT>,
    val groupPrefix: String? = null
)

typealias ExpectationCreatorTriple<SubjectT> = Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>

@Suppress("FunctionName")
fun <T> ExpectationCreatorTriple(
    name: String,
    containsNot: String,
    expectationCreatorOk: Expect<T>.() -> Expect<T>,
    expectationCreatorFail: Expect<T>.() -> Expect<T>
) = Triple(name, containsNot, expectationCreatorOk to expectationCreatorFail)

