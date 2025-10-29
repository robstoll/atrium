package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ErrorMessages
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class AssertionCreatorSpec<T>(
    groupPrefix: String,
    subject: T,
    vararg assertionCreators: ExpectationCreatorTriple<T>
) : Spek({

    describe("${groupPrefix}specifying two assertionCreator-lambda, one is empty") {

        assertionCreators.forEach { (name, stringNotInMessage, lambdas) ->
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
}) {
    constructor(
        groupPrefix: String,
        subject: T,
        assertionCreators: Pair<ExpectationCreatorTriple<T>, ExpectationCreatorTriple<T>>
    ) : this(groupPrefix, subject, assertionCreators.first, assertionCreators.second)
}
