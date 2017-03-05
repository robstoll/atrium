package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker

internal class AssertionFactoryCheckImmediately<out T : Any> constructor(
    assertionVerb: String, subject: T, assertionChecker: IAssertionChecker) : AssertionFactoryCheckLazily<T>(assertionVerb, subject, assertionChecker) {

    override fun addAssertion(assertion: IAssertion): IAssertionFactory<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }
}
