package ch.tutteli.atrium.specs.integration.utils

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.notToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toContainRegex
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.ErrorMessages
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.forElementAndForEachIn

fun <SubjectT> expectationCreatorTestSetup(
    subject: SubjectT,
    expectationCreator: ExpectationCreatorTriple<SubjectT>,
    otherExpectationCreators: Array<out ExpectationCreatorTriple<SubjectT>>,
    //TODO 1.3.0 include check that custom hint is included
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
                        //TODO 1.3.0/1.4.0 introduce an explosion icon for such errors and only write what was wrong, i.e. don't use the to equal approach
                        toContainRegex(
                            ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED.string + "\\s+:\\s+false",
                            ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION.string,
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

