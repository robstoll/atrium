package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class IAssertionCheckerDelegateFailSpec : Spek({

    val assertionVerb = "assertionVerb"

    val testee = spy<IAssertionCheckerDelegateFail>(Dummy())
    describe("fail") {
        it("throws an IllegalArgumentException if the given assertion holds") {
            expect {
                testee.fail(assertionVerb, 1, OneMessageAssertion("description", 1, true))
            }.toThrow<IllegalArgumentException> {
                //TODO check message
            }
        }

        it("delegates to check") {
            val assertion = OneMessageAssertion("description", 1, false)
            testee.fail(assertionVerb, 1, assertion)
            verify(testee).check(assertionVerb, 1, listOf(assertion))
        }
    }
}) {
    open class Dummy : IAssertionCheckerDelegateFail {
        override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
            //do nothing
        }
    }
}
