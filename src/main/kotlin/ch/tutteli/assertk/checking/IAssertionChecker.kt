package ch.tutteli.assertk.checking

import ch.tutteli.assertk.assertions.IAssertion

interface IAssertionChecker {
    fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>)
    fun fail(assertionVerb: String, subject: Any, assertion: IAssertion)
}
