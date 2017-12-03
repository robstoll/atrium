package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Checks given [IAssertion]s and reports if one of them fails.
 */
interface IAssertionChecker {
    /**
     * Checks given [assertions] and reports if one of them fails (does not hold).
     *
     * @param assertionVerb The assertion verb which will be used in error reporting.
     * @param subject The subject for which the [assertions] have been created.
     * @param assertions The [assertions] which are checked.
     *
     * @throws AssertionError An implementation is allowed to throw [AssertionError] if an assertion fails.
     */
    fun check(assertionVerb: ITranslatable, subject: Any, assertions: List<IAssertion>)
}
