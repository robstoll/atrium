package ch.tutteli.assertk

interface IAssertionChecker {
    fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>)
    fun fail(assertionVerb: String, subject: Any, assertion: IAssertion)
}
