package ch.tutteli.assertk

interface IAssertionCheckerDelegateFail : IAssertionChecker {
    override fun fail(assertionVerb: String, subject: Any, assertion: IAssertion) {
        if (assertion.holds()) throw IllegalArgumentException("the given assertion should fail: $assertion")
        check(assertionVerb, subject, listOf(assertion))
    }

    override fun failWithCustomSubject(assertionVerb: String, subject: String, assertion: IAssertion) {
        if (assertion.holds()) throw IllegalArgumentException("the given assertion should fail: $assertion")
        check(assertionVerb, subject, listOf(assertion))
    }
}
