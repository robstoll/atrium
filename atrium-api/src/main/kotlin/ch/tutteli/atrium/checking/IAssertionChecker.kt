package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Checks given [IAssertion]s and reports if one of them has failed.
 */
interface IAssertionChecker {
    /**
     * Checks given [assertions] and reports if one of them has failed.
     *
     * @throws AssertionError An implementation is allowed to throw [AssertionError] if an assertion fails
     */
    fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>)

    /**
     * Reports that the given [assertion] failed.
     * @throws IllegalArgumentException in case the [assertion] holds
     */
    fun fail(assertionVerb: String, subject: Any, assertion: IAssertion)
}
