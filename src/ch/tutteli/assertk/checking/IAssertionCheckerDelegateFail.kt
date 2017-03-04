package ch.tutteli.assertk.checking

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker

interface IAssertionCheckerDelegateFail : IAssertionChecker {
    override fun fail(assertionVerb: String, subject: Any, assertion: IAssertion) {
        if (assertion.holds()) throw IllegalArgumentException(THE_GIVEN_ASSERTION_SHOULD_FAIL + assertion)
        check(assertionVerb, subject, listOf(assertion))
    }

    companion object {
        internal val THE_GIVEN_ASSERTION_SHOULD_FAIL = "the given assertion should fail: "
    }
}
