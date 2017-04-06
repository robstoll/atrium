package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Provides a default-implementation for [IAssertionChecker.fail] which first checks
 * that the given [IAssertion] fails and then delegates to [IAssertionChecker.check].
 */
interface IAssertionCheckerDelegateFail : IAssertionChecker {
    /**
     * Delegates to [check] if the assertion fails
     * @throws IllegalArgumentException in case the given [assertion] holds
     */
    override fun fail(assertionVerb: String, subject: Any, assertion: IAssertion) {
        if (assertion.holds()) throw IllegalArgumentException(THE_GIVEN_ASSERTION_SHOULD_FAIL + assertion)
        check(assertionVerb, subject, listOf(assertion))
    }

    companion object {
        internal val THE_GIVEN_ASSERTION_SHOULD_FAIL = "the given assertion should fail: "
    }
}
