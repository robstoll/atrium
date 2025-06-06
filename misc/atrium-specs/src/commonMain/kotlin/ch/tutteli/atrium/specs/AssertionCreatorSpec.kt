package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ErrorMessages
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class AssertionCreatorSpec<T>(
    groupPrefix: String,
    subject: T,
    vararg assertionCreator: Triple<String, String, Pair<Expect<T>.() -> Expect<T>, Expect<T>.() -> Expect<T>>>
) : Spek({

    describe("${groupPrefix}specifying two assertionCreator-lambda, one is empty") {

        assertionCreator.forEach { (name, stringNotInMessage, lambdas) ->
            val (createAssertionOk, createAssertionFail) = lambdas
            describe("fun `$name`") {
                it("throws and only reports the lambda which was empty") {
                    expect {
                        expect(subject)
                            .createAssertionOk()
                            .createAssertionFail()
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
})
