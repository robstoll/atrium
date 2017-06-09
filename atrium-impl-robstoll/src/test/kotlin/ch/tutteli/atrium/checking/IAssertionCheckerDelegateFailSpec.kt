package ch.tutteli.atrium.checking

import ch.tutteli.atrium.AssertionVerb
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.expect
import ch.tutteli.atrium.message
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.startsWith
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object IAssertionCheckerDelegateFailSpec : Spek({

    val assertionVerb = AssertionVerb.ASSERT

    val testee = spy<IAssertionCheckerDelegateFail>(Dummy())
    describe("fail") {
        it("throws an IllegalArgumentException if the given assertion holds") {
            expect {
                testee.fail(assertionVerb, 1, OneMessageAssertion(ch.tutteli.atrium.DescriptionAnyAssertion.IS_SAME, 1, true))
            }.toThrow<IllegalArgumentException>().and.message.startsWith(IAssertionCheckerDelegateFail.THE_GIVEN_ASSERTION_SHOULD_FAIL)
        }

        it("delegates to check") {
            val assertion = OneMessageAssertion(ch.tutteli.atrium.DescriptionAnyAssertion.IS_SAME, 1, false)
            testee.fail(assertionVerb, 1, assertion)
            verify(testee).check(assertionVerb, 1, listOf(assertion))
        }
    }
}) {
    open class Dummy : IAssertionCheckerDelegateFail {
        override fun check(assertionVerb: ITranslatable, subject: Any, assertions: List<IAssertion>) {
            //do nothing
        }
    }
}
